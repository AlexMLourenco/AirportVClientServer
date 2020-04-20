package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;
import stubs.RepositoryStub;

public class BaggageReclaimOffice implements SharedRegionInterface {

    private RepositoryStub repositoryStub;


    public BaggageReclaimOffice(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
    }

    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passengers reports a missing bag
     *
     */
    public synchronized void reportMissingBag(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repositoryStub.setPassengerState(passenger.getIdentifier(), PassengerStates.AT_THE_BAGGAGE_RECLAIM_OFFICE);
        //repository.bagsLost++; //TODO: ISTO NAO PODE SER ASSIM
        try {
            passenger.sleep(2000);
        } catch (Exception e) { }

    }

}
