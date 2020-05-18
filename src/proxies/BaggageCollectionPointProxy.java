package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.BaggageCollectionPoint;

public class BaggageCollectionPointProxy implements SharedRegionProxyInterface {

    private static BaggageCollectionPoint baggageCollectionPoint;

    private boolean simulationFinished = false;

    public BaggageCollectionPointProxy(BaggageCollectionPoint baggageCollectionPoint) {
        this.baggageCollectionPoint = baggageCollectionPoint;

    }

    public Message processAndReply(Message message) {

        Message response = new Message();
        System.out.println("Processing Message Type: " + message.getMessageType());


        switch(message.getMessageType()) {
            case BAGGAGE_COLLECTION_POINT_WARNING_NO_MORE_BAGS_IN_PLANE_HOLD:
                 baggageCollectionPoint.warningNoMoreBagsInThePlaneHold();
                 break;
            case BAGGAGE_COLLECTION_POINT_CARRY_IT_TO_APPROPRIATE_STORE:
                 baggageCollectionPoint.carryItToAppropriateStore(message.getBag());
                 break;
            case BAGGAGE_COLLECTION_POINT_GO_COLLECT_BAG:
                response.setIntValue(baggageCollectionPoint.goCollectBag(message.getIdentifier()));
                break;
            case BAGGAGE_COLLECTION_POINT_CLEAN_UP:
                baggageCollectionPoint.cleanUp();
                break;
            case BAGGAGE_COLLECTION_POINT_SIMULATION_FINISHED:
                this.simulationFinished = true;
                break;
        }

        response.setMessageType(MessageType.REPLY_OK);
        System.out.println("Replying Message: " + response);
        return response;
    }

    public boolean simulationFinished() {
        return this.simulationFinished;
    }


}
