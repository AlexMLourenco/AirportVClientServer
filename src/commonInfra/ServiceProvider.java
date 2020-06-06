/**
 * <h1>Server Provider</h1>
 * The Server Provider class implements the method to
 * create a Service that allows a shared region to establish
 * connection with a server and exchange messages between them
 */


/**
 * commonInfra is a group of bar utils for operating on foo things.
 */
package commonInfra;

import proxies.SharedRegionProxyInterface;

public class ServiceProvider extends Thread  {

    private SharedRegionProxyInterface sharedRegion;
    private ServerCom serverCom;

    /**
     * ServerCom constructor.
     * Creates a Service with the specified server and shared region
     * @param sharedRegion Region to be connected
     * @param serverCom    Server to establish the connection
     */
    public ServiceProvider(SharedRegionProxyInterface sharedRegion, ServerCom serverCom) {
        this.sharedRegion = sharedRegion;
        this.serverCom = serverCom;
    }

    /**
     * Extended version of {@link #run()}.
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
