package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.BaggageReclaimOffice;

public class BaggageReclaimOfficeProxy implements SharedRegionProxyInterface {

    private static BaggageReclaimOffice baggageReclaimOffice;

    public BaggageReclaimOfficeProxy(BaggageReclaimOffice baggageReclaimOffice) {
        this.baggageReclaimOffice = baggageReclaimOffice;

    }

    public Message processAndReply(Message message) {

        Message response = new Message();
        System.out.println("Processing Message Type: " + message.getMessageType());
        switch(message.getMessageType()) {
            case BAGGAGE_RECLAIM_OFFICE_RECLAIM_MISSING_BAG:
                baggageReclaimOffice.reportMissingBag(message.getIdentifier());
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
