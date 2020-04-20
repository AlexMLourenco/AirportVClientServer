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
        char action = arrivalLoungeStub.whatShouldIDo(identifier, isFinalDestination, numberOfLuggages);

        switch (action) {
            case 'B':  // B - Take a bus
                arrivalTerminalTransferQuayStub.takeABus(identifier);
                arrivalTerminalTransferQuayStub.waitForBus(identifier);
                arrivalTerminalTransferQuayStub.enterTheBus(identifier);
                departureTerminalTransferQuayStub.leaveTheBus(identifier);
                departureTerminalEntranceStub.prepareNextLeg(identifier);
                break;
            case 'C':   // C - Collect Bag
                baggageCollectionPointStub.goCollectBag();
                if (this.numberOfCollectedLuggages != numberOfLuggages) {
                    baggageReclaimOfficeStub.reportMissingBag();
                }
                arrivalTerminalExitStub.goHome();
                break;
            case 'H':   // H - Go Home
                arrivalTerminalExitStub.goHome();
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
