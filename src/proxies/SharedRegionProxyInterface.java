package proxies;

import common.Message;

public interface SharedRegionProxyInterface {

    public Message processAndReply(Message message);

    public boolean simulationFinished();


}
