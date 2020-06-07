/**
 * <h1>GenericStub </h1>
 * GenericStub Class super class that implements the common methods to stubs
 */
package stubs;

import commonInfra.ClientCom;
import commonInfra.Message;

public class GenericStub {

    private String hostname;
    private int port;

    /**
     * Generic Stub instatiation
     * @param hostname
     * @param port
     */
    public GenericStub(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Processing message
     * @param message to be processed
     * @return the message
     */
    public Message process(Message message) {
        ClientCom cc = new ClientCom(hostname,port);
        cc.open();
        cc.writeObject(message);

        return (Message) cc.readObject();
    }

    /**
     * Returns the Stub Hostname
     * @return the Stub Hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Updates the Stub Hostname
     * @param hostname new hostname
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Returns the Stub Port
     * @return the Stub Port
     */
    public int getPort() {
        return port;
    }

    /**
     * Updates the Stub Port
     * @param new port number
     */
    public void setPort(int port) {
        this.port = port;
    }


}

