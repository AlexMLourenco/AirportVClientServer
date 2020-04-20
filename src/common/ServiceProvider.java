package common;

import proxies.SharedRegionProxyInterface;

public class ServiceProvider extends Thread implements PorterInterface, PassengerInterface, BusDriverInterface {

    private SharedRegionProxyInterface sharedRegion;
    private ServerCom serverCom;

    public ServiceProvider(SharedRegionProxyInterface sharedRegion, ServerCom serverCom) {
        this.sharedRegion = sharedRegion;
        this.serverCom = serverCom;
    }

    /**
     * Waits for message and sends it to shared region to be processed
     */
    @Override
    public void run() {
        Message msg = (Message) serverCom.readObject();
        msg = sharedRegion.processAndReply(msg);
        serverCom.writeObject(msg);
        serverCom.close();
    }



}
