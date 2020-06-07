/**
 * <h1>Porter</h1>
 * The Porter class implements methods to create a Porter, his lifecycle
 * and all the possible actions he's able to do
 */


package entities;

import commonInfra.BAG;
import sharedRegions.ArrivalLounge;
import sharedRegions.BaggageCollectionPoint;
import sharedRegions.TemporaryStorageArea;
import stubs.ArrivalLoungeStub;
import stubs.BaggageCollectionPointStub;
import stubs.TemporaryStorageAreaStub;

public class Porter extends Thread {

    private ArrivalLoungeStub arrivalLoungeStub;
    private TemporaryStorageAreaStub temporaryStorageAreaStub;
    private BaggageCollectionPointStub baggageCollectionPointStub;

    private StateInterface entityState;

    private boolean keepAlive;

    /**
     * BusDriver constructor.
     * Creates a BusDriver in the specific shared regions
     * @param arrivalLoungeStub Arrival Lounge shared region
     * @param temporaryStorageAreaStub Temporary Storage Area shared region
     * @param baggageCollectionPointStub Collection Bag Point shared region
     */
    public Porter(ArrivalLoungeStub arrivalLoungeStub,
                  TemporaryStorageAreaStub temporaryStorageAreaStub,
                  BaggageCollectionPointStub baggageCollectionPointStub) {
        super("Porter");
        this.arrivalLoungeStub= arrivalLoungeStub;
        this.temporaryStorageAreaStub= temporaryStorageAreaStub;
        this.baggageCollectionPointStub = baggageCollectionPointStub;
        this.keepAlive = true;
    }

    /**
     * Extended version of {@link #run()}.
     * Porter's lifecycle
     *      while Porter doesn't reach the end of the day he will collect
     *      the bags of the passengers and carry them to the AppropiateStore
     */
    @Override
    public void run() {
        Boolean planeHoldEmpty;
        while (true) {
            arrivalLoungeStub.takeARest();
            if (!this.keepAlive) break;

            planeHoldEmpty = arrivalLoungeStub.noMoreBagsToCollect();
            if (planeHoldEmpty) baggageCollectionPointStub.warningNoMoreBagsInThePlaneHold();

            while (!planeHoldEmpty) {

                BAG bag = arrivalLoungeStub.tryToCollectABag();
                if (bag.isFinalDestination()) {
                    baggageCollectionPointStub.carryItToAppropriateStore(bag);
                } else {
                    temporaryStorageAreaStub.carryItToAppropriateStore(bag); }

                planeHoldEmpty = arrivalLoungeStub.noMoreBagsToCollect();

                if (planeHoldEmpty) baggageCollectionPointStub.warningNoMoreBagsInThePlaneHold();
            }
        }
    }
    /**
     * Returns the current state of the Porter.
     * @return entityState the current state of the Porter
     */
    public StateInterface getEntityState() {
        return entityState;
    }

    /** Updates the state of the porter.
     * @param entityState the state that porter will assume
     * @return Nothing
     */
    public void setEntityState(StateInterface entityState) {
        this.entityState = entityState;
    }

    /** Updates the boolean keepAlive.
     * @param keepAlive {@code true} means the day isn't over yet
     *             otherwise {@code false}
     */
    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
