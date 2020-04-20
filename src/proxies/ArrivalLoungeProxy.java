package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.ArrivalLounge;

public class ArrivalLoungeProxy implements SharedRegionProxyInterface {

    private final ArrivalLounge arrivalLounge;

    public ArrivalLoungeProxy(ArrivalLounge arrivalLounge) {
        this.arrivalLounge = arrivalLounge;

    }

    public Message processAndReply(Message message) {
        Message response = new Message();
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(message.getMessageType()) {
            case ARRIVAL_LOUNGE_INIT_PLANE_HOLD:
                arrivalLounge.init_plane_hold(message.getIntValue());
                break;
            case ARRIVAL_LOUNGE_TAKE_A_REST:
                arrivalLounge.takeARest();
                break;
            case ARRIVAL_LOUNGE_NO_MORE_BAGS_TO_COLLECT:
                message.setBooleanValue(arrivalLounge.noMoreBagsToCollect());
                break;
            case ARRIVAL_LOUNGE_TRY_TO_COLLECT_A_BAG:
                message.setBag(arrivalLounge.tryToCollectABag());
                break;
            case ARRIVAL_LOUNGE_WHAT_SHOULD_I_DO:
                message.setCharValue(arrivalLounge.whatShouldIDo(message.getIdentifier(), message.getBooleanValue(), message.getIntValue()));
                break;
        }
        return response;
    }

    public boolean simulationFinished() {
        return false;
    }


}
