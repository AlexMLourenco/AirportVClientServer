package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.ArrivalTerminalTransferQuay;

public class ArrivalTerminalTransferQuayProxy implements SharedRegionProxyInterface {

    private final ArrivalTerminalTransferQuay arrivalTerminalTransferQuay;

    public ArrivalTerminalTransferQuayProxy(ArrivalTerminalTransferQuay arrivalTerminalTransferQuay) {
        this.arrivalTerminalTransferQuay = arrivalTerminalTransferQuay;

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
