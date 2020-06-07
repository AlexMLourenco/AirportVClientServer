/**
 * <h1>Baggage Reclaim Office Proxy</h1>
 * The BaggageReclaimOfficeProxy class implements SharedRegionProxyInterface methods
 * to proccess and reply the messages and checks if the simulation is finished
 * in Baggage Reclaim Office shared region
 */

package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.BaggageReclaimOffice;

public class BaggageReclaimOfficeProxy implements SharedRegionProxyInterface {

    private static BaggageReclaimOffice baggageReclaimOffice;

    private boolean simulationFinished = false;
    /**
     * BaggageReclaimOfficeProxy constructor.
     * Creates a BaggageReclaimOfficeProxy in Baggage Reclaim Office  Shared Region
     * @param baggageReclaimOffice that corresponds to Baggage Reclaim Office  Shared Region
     */
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
