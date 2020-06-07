/**
 * <h1>Shared Region Proxy Interface</h1>
 * Interface that implements the method processAndReply(Message message)()
 * process and reply messages and to check if simulation has finished
 */


package proxies;

import commonInfra.Message;

public interface SharedRegionProxyInterface {
    /**
     * Process and Reply messages depending on message type
     * @param message that corresponds to the specific message
     * @return response Message reply
     */
    public Message processAndReply(Message message);

    /** checks if the simulation has finished.
     * @return simulationFinished {@code true} means the the simulation has finished
     *                   otherwise {@code false}
     */
    public boolean simulationFinished();


}
