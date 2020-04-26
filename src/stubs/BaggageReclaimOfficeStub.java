package stubs;

import commonInfra.Message;
import commonInfra.MessageType;

public class BaggageReclaimOfficeStub extends GenericStub{

    public BaggageReclaimOfficeStub(String hostname, int port) {
        super(hostname, port);
    }

    public void reportMissingBag(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_RECLAIM_OFFICE_RECLAIM_MISSING_BAG);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);

    }
}
