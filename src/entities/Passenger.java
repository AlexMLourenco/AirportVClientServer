package entities;

import stubs.*;

public class Passenger extends Thread {
    private int identifier;
    private boolean isFinalDestination;
    private int numberOfLuggages;
    private int numberOfCollectedLuggages;

    private ArrivalLoungeStub arrivalLoungeStub;
    private ArrivalTerminalTransferQuayStub arrivalTerminalTransferQuayStub;
    private ArrivalTerminalExitStub arrivalTerminalExitStub;
    private DepartureTerminalTransferQuayStub departureTerminalTransferQuayStub ;
    private DepartureTerminalEntranceStub departureTerminalEntranceStub;
    private BaggageCollectionPointStub baggageCollectionPointStub;
    private BaggageReclaimOfficeStub baggageReclaimOfficeStub;

    public Passenger(int identifier,
                     int numberOfLuggages,
                     boolean isFinalDestination,
                     ArrivalLoungeStub arrivalLoungeStub,
                     ArrivalTerminalTransferQuayStub arrivalTerminalTransferQuayStub,
                     ArrivalTerminalExitStub arrivalTerminalExitStub,
                     DepartureTerminalTransferQuayStub departureTerminalTransferQuayStub,
                     DepartureTerminalEntranceStub departureTerminalEntranceStub,
                     BaggageCollectionPointStub baggageCollectionPointStub,
                     BaggageReclaimOfficeStub baggageReclaimOfficeStub){

        super("Passenger "+ identifier);
        this.identifier = identifier;
        this.numberOfLuggages = numberOfLuggages;
        this.isFinalDestination = isFinalDestination;
        this.numberOfCollectedLuggages = 0;
        this.arrivalLoungeStub = arrivalLoungeStub;
        this.arrivalTerminalTransferQuayStub = arrivalTerminalTransferQuayStub;
        this.arrivalTerminalExitStub = arrivalTerminalExitStub;
        this.departureTerminalTransferQuayStub = departureTerminalTransferQuayStub;
        this.departureTerminalEntranceStub = departureTerminalEntranceStub;
        this.baggageCollectionPointStub = baggageCollectionPointStub;
        this.baggageReclaimOfficeStub = baggageReclaimOfficeStub;
    }

    public int getIdentifier() {
        return identifier;
    }


    @Override
    public void run() {
        System.out.println("Passenger " + identifier + ": start");
        char action = arrivalLoungeStub.whatShouldIDo(identifier, isFinalDestination, numberOfLuggages);

        System.out.println("Passenger " + identifier + ": whatShouldIDo action " + action );
        switch (action) {
            case 'B':  // B - Take a bus
                arrivalTerminalTransferQuayStub.takeABus(identifier);
                System.out.println("Passenger " + identifier + ": takeABus" );
                arrivalTerminalTransferQuayStub.waitForBus(identifier);
                System.out.println("Passenger " + identifier + ": waitForBus" );
                arrivalTerminalTransferQuayStub.enterTheBus(identifier);
                System.out.println("Passenger " + identifier + ": enterTheBus" );
                departureTerminalTransferQuayStub.leaveTheBus(identifier);
                System.out.println("Passenger " + identifier + ": leaveTheBus" );
                departureTerminalEntranceStub.prepareNextLeg(identifier);
                System.out.println("Passenger " + identifier + ": prepareNextLeg" );
                break;
            case 'C':   // C - Collect Bag
                this.numberOfCollectedLuggages = baggageCollectionPointStub.goCollectBag(identifier);
                if (this.numberOfCollectedLuggages != numberOfLuggages) {
                    baggageReclaimOfficeStub.reportMissingBag(identifier);
                }
                arrivalTerminalExitStub.goHome(identifier);
                break;
            case 'H':   // H - Go Home
                arrivalTerminalExitStub.goHome(identifier);
                break;
        }

    }

    /**
     * Increase the number of luggage collected
     * */
    public void increaseCollectedLuggages() {
        this.numberOfCollectedLuggages++;
    }

}
