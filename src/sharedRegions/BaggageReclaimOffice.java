/**
 * <h1>Baggage Reclaim Office </h1>
 * BaggageReclaimOffice Class implements SharedRegionInterface and the Baggage Reclaim Office shared memory region.
 * In this shared region, is where the passenger goes to report a missing bag
 * and the passengers pick them out
 *
 */
package sharedRegions;

import entities.PassengerStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class BaggageReclaimOffice implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    /**
     * BaggageReclaimOffice constructor.
     * Creates a BaggageReclaimOffice in repository Stub
     * @param repositoryStub that corresponds to Stub Repository
     */
    public BaggageReclaimOffice(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
    }

    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passenger reportMissingBag(int identifier) method
     * @param identifier Passenger identifier
     * if the bag has missing bags its reported
     */
    public synchronized void reportMissingBag(int identifier){
        repositoryStub.setPassengerState(identifier, PassengerStates.AT_THE_BAGGAGE_RECLAIM_OFFICE);
        try {
            Thread.currentThread().sleep(SimulPar.REPORT_MISSING_BAG_SLEEP);
        } catch (Exception e) { }

    }

}
