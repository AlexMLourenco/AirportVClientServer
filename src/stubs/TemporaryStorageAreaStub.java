package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
import commonInfra.BAG;

public class TemporaryStorageAreaStub extends GenericStub{

    public TemporaryStorageAreaStub(String hostname, int port) {
        super(hostname, port);
    }

    public void  warningNoMoreBagsInThePlaneHold() {

    }

    public void carryItToAppropriateStore(BAG bag) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.TEMPORARY_STORAGE_AREA_CARRY_IT_TO_APPROPRIATE_STORE);
        outMessage.setBag(bag);

        this.process(outMessage);

    }
}
