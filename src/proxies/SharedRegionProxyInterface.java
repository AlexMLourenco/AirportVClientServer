package proxies;

import commonInfra.Message;

public interface SharedRegionProxyInterface {

    public Message processAndReply(Message message);

    public boolean simulationFinished();


}
