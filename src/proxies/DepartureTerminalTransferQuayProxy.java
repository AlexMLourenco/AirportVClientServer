package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.DepartureTerminalEntrance;
import sharedRegions.DepartureTerminalTransferQuay;

public class DepartureTerminalTransferQuayProxy implements SharedRegionProxyInterface {

    private final DepartureTerminalTransferQuay departureTerminalTransferQuay;

    public DepartureTerminalTransferQuayProxy(DepartureTerminalTransferQuay departureTerminalTransferQuay) {
        this.departureTerminalTransferQuay = departureTerminalTransferQuay;
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
