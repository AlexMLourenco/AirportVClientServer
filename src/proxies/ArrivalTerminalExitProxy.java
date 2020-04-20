package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.ArrivalTerminalExit;

public class ArrivalTerminalExitProxy implements SharedRegionProxyInterface {

    private final ArrivalTerminalExit arrivalTerminalExit;

    public ArrivalTerminalExitProxy(ArrivalTerminalExit arrivalTerminalExit) {
        this.arrivalTerminalExit = arrivalTerminalExit;
    }

    public Message processAndReply(Message message){
        Message response = new Message();
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(message.getMessageType()) {
            case ARRIVAL_TERMINAL_EXIT_GO_HOME:
                arrivalTerminalExit.goHome();
                break;
        }
        return response;
    }


    public boolean simulationFinished() {
        return false;
    }


}
