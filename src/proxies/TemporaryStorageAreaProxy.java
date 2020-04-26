package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.TemporaryStorageArea;

public class TemporaryStorageAreaProxy implements SharedRegionProxyInterface {

    private final TemporaryStorageArea temporaryStorageArea;

    public TemporaryStorageAreaProxy(TemporaryStorageArea temporaryStorageArea) {
        this.temporaryStorageArea = temporaryStorageArea;
    }
    public Message processAndReply(Message message) {

        Message response = new Message();
        System.out.println("Processing Message Type: " + message.getMessageType());

        switch(message.getMessageType()) {
            case TEMPORARY_STORAGE_AREA_CARRY_IT_TO_APPROPRIATE_STORE:
                temporaryStorageArea.carryItToAppropriateStore(message.getBag());
                break;
        }
        response.setMessageType(MessageType.REPLY_OK);
        System.out.println("Replying Message: " + response);
        return response;
    }

    public boolean simulationFinished() {
        return false;
    }


}
