/**
 * <h1>TemporaryStorageAreaProxy</h1>
 * The TemporaryStorageAreaProxy class implements SharedRegionProxyInterface methods
 * to proccess and reply the messages and checks if the simulation is finished
 * in Temporary Storage Area shared region
 */

package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.TemporaryStorageArea;

public class TemporaryStorageAreaProxy implements SharedRegionProxyInterface {

    private final TemporaryStorageArea temporaryStorageArea;

    private boolean simulationFinished = false;
    /**
     * TemporaryStorageAreaProxy constructor.
     * Creates a TemporaryStorageAreaProxy in Temporary Storage Area Shared Region
     * @param temporaryStorageArea that corresponds to Temporary Storage Area Shared Region
     */
    public TemporaryStorageAreaProxy(TemporaryStorageArea temporaryStorageArea) {
        this.temporaryStorageArea = temporaryStorageArea;
    }
    public Message processAndReply(Message message) {

        Message response = new Message();

        switch(message.getMessageType()) {
            case TEMPORARY_STORAGE_AREA_CARRY_IT_TO_APPROPRIATE_STORE:
                temporaryStorageArea.carryItToAppropriateStore(message.getBag());
                break;
            case TEMPORARY_STORAGE_AREA_SIMULATION_FINISHED:
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
