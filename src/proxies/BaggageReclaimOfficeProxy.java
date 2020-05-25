package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.BaggageReclaimOffice;

public class BaggageReclaimOfficeProxy implements SharedRegionProxyInterface {

    private static BaggageReclaimOffice baggageReclaimOffice;

    private boolean simulationFinished = false;

    public BaggageReclaimOfficeProxy(BaggageReclaimOffice baggageReclaimOffice) {
        this.baggageReclaimOffice = baggageReclaimOffice;

    }

    public Message processAndReply(Message message) {

        Message response = new Message();
        switch(message.getMessageType()) {
            case BAGGAGE_RECLAIM_OFFICE_RECLAIM_MISSING_BAG:
                baggageReclaimOffice.reportMissingBag(message.getIdentifier());
                break;
            case BAGGAGE_RECLAIM_OFFICE_SIMULATION_FINISHED:
                this.simulationFinished = true;
                break;
        }
        response.setMessageType(MessageType.REPLY_OK);
        return response;
    }

    public boolean simulationFinished() {
        return this.simulationFinished;
    }


}
