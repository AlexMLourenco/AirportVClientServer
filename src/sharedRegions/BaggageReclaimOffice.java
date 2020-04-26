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

    /** DONE **/
    public synchronized void reportMissingBag(int identifier){
        repositoryStub.setPassengerState(identifier, PassengerStates.AT_THE_BAGGAGE_RECLAIM_OFFICE);
        try {
            Thread.currentThread().sleep(2000);
        } catch (Exception e) { }

    }

}
