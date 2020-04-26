package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
import commonInfra.BAG;

public class BaggageCollectionPointStub extends GenericStub{

    public BaggageCollectionPointStub(String hostname, int port) {
        super(hostname, port);
    }

    /*** DONE ***/
    public void warningNoMoreBagsInThePlaneHold() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_COLLECTION_POINT_WARNING_NO_MORE_BAGS_IN_PLANE_HOLD);

        this.process(outMessage);
    }

    public void carryItToAppropriateStore(BAG bag) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_COLLECTION_POINT_CARRY_IT_TO_APPROPRIATE_STORE);
        outMessage.setBag(bag);

        this.process(outMessage);

    }

    public int goCollectBag(int identifier) {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_COLLECTION_POINT_GO_COLLECT_BAG);
        outMessage.setIdentifier(identifier);

        inMessage = this.process(outMessage);

        return inMessage.getIntValue();
    }
}
