/**
 * <h1>Arrival Terminal Exit Proxy</h1>
 * The ArrivalTerminalExitProxy class implements SharedRegionProxyInterface methods
 * to proccess and reply the messages and checks if the simulation is finished
 * in Arrival Terminal Exit shared region
 /**
 * Package to process the messages
 */

package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.ArrivalTerminalExit;

public class ArrivalTerminalExitProxy implements SharedRegionProxyInterface {

    private final ArrivalTerminalExit arrivalTerminalExit;

    private boolean simulationFinished = false;

    /**
     * ArrivalTerminalExitProxy constructor.
     * Creates a ArrivalTerminalExitProxy in Arrival Terminal Exit Shared Region
     * @param arrivalTerminalExit that corresponds to Arrival Terminal Exit Shared Region
     */
    public ArrivalTerminalExitProxy(ArrivalTerminalExit arrivalTerminalExit) {
        this.arrivalTerminalExit = arrivalTerminalExit;
    }

    public Message processAndReply(Message message){
        Message response = new Message();
        switch(message.getMessageType()) {
            case ARRIVAL_TERMINAL_EXIT_GO_HOME:
                arrivalTerminalExit.goHome(message.getIdentifier());
                break;
            case ARRIVAL_TERMINAL_EXIT_CLEAN_UP:
                arrivalTerminalExit.cleanUp();
                break;
            case ARRIVAL_TERMINAL_EXIT_GET_NUMBER_OF_PASSENGERS:
                response.setIntValue(arrivalTerminalExit.getNumberOfPassengers());
                break;
            case ARRIVAL_TERMINAL_EXIT_READY_TO_LEAVE:
                arrivalTerminalExit.readyToLeave();
                break;
            case ARRIVAL_TERMINAL_SIMULATION_FINISHED:
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
