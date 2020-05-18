package sharedRegions;

import entities.PassengerStates;
import mainProject.SimulPar;
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
            Thread.currentThread().sleep(SimulPar.REPORT_MISSING_BAG_SLEEP);
        } catch (Exception e) { }

    }

}
