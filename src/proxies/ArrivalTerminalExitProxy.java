package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.ArrivalTerminalExit;

public class ArrivalTerminalExitProxy implements SharedRegionProxyInterface {

    private final ArrivalTerminalExit arrivalTerminalExit;

    private boolean simulationFinished = false;


    public ArrivalTerminalExitProxy(ArrivalTerminalExit arrivalTerminalExit) {
        this.arrivalTerminalExit = arrivalTerminalExit;
    }

    public Message processAndReply(Message message){
        Message response = new Message();
        System.out.println("Processing Message Type: " + message.getMessageType());
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
        System.out.println("Replying Message: " + response);
        return response;
    }


    public boolean simulationFinished() {
        return this.simulationFinished;
    }


}
