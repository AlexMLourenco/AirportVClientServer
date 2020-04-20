package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.BaggageReclaimOffice;

public class BaggageReclaimOfficeProxy implements SharedRegionProxyInterface {

    private static BaggageReclaimOffice baggageReclaimOffice;

    public BaggageReclaimOfficeProxy(BaggageReclaimOffice baggageReclaimOffice) {
        this.baggageReclaimOffice = baggageReclaimOffice;

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
