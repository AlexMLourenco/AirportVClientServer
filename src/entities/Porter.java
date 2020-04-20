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

    public Porter(ArrivalLoungeStub arrivalLoungeStub,
                  TemporaryStorageAreaStub temporaryStorageAreaStub,
                  BaggageCollectionPointStub baggageCollectionPointStub) {
        super("Porter");
        this.arrivalLoungeStub= arrivalLoungeStub;
        this.temporaryStorageAreaStub= temporaryStorageAreaStub;
        this.baggageCollectionPointStub = baggageCollectionPointStub;
        this.keepAlive = true;
    }

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

    public StateInterface getEntityState() {
        return entityState;
    }

    public void setEntityState(StateInterface entityState) {
        this.entityState = entityState;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
