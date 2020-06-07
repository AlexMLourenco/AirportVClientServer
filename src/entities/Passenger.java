/**
 * <h1>Passenger</h1>
 * The Passenger class implements methods to create a Passenger, his lifecycle
 * and all the possible actions he's able to do
 */

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
    /**
     * Passenger constructor.
     * Creates a BusDriver in the specific shared regions
     * @param identifier the id of the passenger
     * @param numberOfLuggages the number of luggages of the passenger
     * @param isFinalDestination boolean to identify if passenger arrives his final destination
     * @param arrivalLoungeStub Arrival lounge shared region
     * @param arrivalTerminalTransferQuayStub Arrival Terminal Transfer quay shared region
     * @param arrivalTerminalExitStub Arrival Terminal Exit shared region
     * @param departureTerminalTransferQuayStub Departure Terminal Transfer quay shared region
     * @param departureTerminalEntranceStub Departure Terminal Entrance shared region
     * @param baggageCollectionPointStub Baggage Collection Point shared region
     * @param  baggageReclaimOfficeStub Baggage Reclaim Office shared region
     */
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

    /**
     * Returns the id of the passenger.
     * @return value of the id of the passenger
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Extended version of {@link #run()}.
     * Passengers's lifecycle
     *      Depending on the destination, number of luggages, passenger can take 3 different actions:
     *          Take a Bus -> if passenger isn't in his final destination yet and needs to go to departure terminal
     *          Collect a Bag -> if passenger reaches his final destination and needs to pick up his luggage
     *                           on baggage collection point
     *                              if his luggage is not found, he needs to go to reclaim office
     *                              and then go home
     *          Go Home -> passenger leaves the airport
     */
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
