package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.DepartureTerminalEntrance;

public class DepartureTerminalEntranceProxy implements SharedRegionProxyInterface {

    private final DepartureTerminalEntrance departureTerminalEntrance;

    public DepartureTerminalEntranceProxy(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
    }

    public Message processAndReply(Message message) {

        Message response = new Message();
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(message.getMessageType()) {
        }
        return response;
    }

    public boolean simulationFinished() {
        return false;
    }


}
