package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.ArrivalTerminalTransferQuay;
import sharedRegions.BaggageCollectionPoint;

public class BaggageCollectionPointProxy implements SharedRegionProxyInterface {

    private static BaggageCollectionPoint baggageCollectionPoint;

    public BaggageCollectionPointProxy(BaggageCollectionPoint baggageCollectionPoint) {
        this.baggageCollectionPoint = baggageCollectionPoint;

    }

    public Message processAndReply(Message message) {

        Message response = new Message();
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(message.getMessageType()) {
        }
        return response;
    }

    public boolean simulationFinished() {
        return false;
    }


}
