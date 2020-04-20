package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.TemporaryStorageArea;

public class TemporaryStorageAreaProxy implements SharedRegionProxyInterface {

    private final TemporaryStorageArea temporaryStorageArea;

    public TemporaryStorageAreaProxy(TemporaryStorageArea temporaryStorageArea) {
        this.temporaryStorageArea = temporaryStorageArea;
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
