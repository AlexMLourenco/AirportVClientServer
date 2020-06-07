/**
 * <h1>Arrival Lounge Proxy</h1>
 * The ArrivalLoungeProxy class implements SharedRegionProxyInterface methods
 * to proccess and reply the messages and checks if the simulation is finished
 * in Arrival Lounge shared region
/**
 * Package to process the messages
 */

package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.ArrivalLounge;

public class ArrivalLoungeProxy implements SharedRegionProxyInterface {

    private final ArrivalLounge arrivalLounge;

    private boolean simulationFinished = false;

    /**
     * ArrivalLoungeProxy constructor.
     * Creates a ArrivalLoungeProxy in Arrival Lounge Shared Region
     * @param arrivalLounge that corresponds to Arrival Lounge Shared Region
     */
    public ArrivalLoungeProxy(ArrivalLounge arrivalLounge) {
        this.arrivalLounge = arrivalLounge;

    }

    public Message processAndReply(Message message) {
        Message response = new Message();

        switch(message.getMessageType()) {
            case ARRIVAL_LOUNGE_TAKE_A_REST:
                arrivalLounge.takeARest();
                break;
            case ARRIVAL_LOUNGE_INIT_PLANE_HOLD:
                arrivalLounge.init_plane_hold(message.getIntValue(), message.getIntArray(), message.getBooleanArray());
                break;
            case ARRIVAL_LOUNGE_SET_PORTER_END_OF_WORK:
                arrivalLounge.setPorterEndOfWork();
                break;
            case ARRIVAL_LOUNGE_NO_MORE_BAGS_TO_COLLECT:
                response.setBooleanValue(arrivalLounge.noMoreBagsToCollect());
                break;
            case ARRIVAL_LOUNGE_TRY_TO_COLLECT_A_BAG:
                response.setBag(arrivalLounge.tryToCollectABag());
                break;
            case ARRIVAL_LOUNGE_WHAT_SHOULD_I_DO:
                response.setCharValue(arrivalLounge.whatShouldIDo(message.getIdentifier(), message.getBooleanValue(), message.getIntValue()));
                break;
            case ARRIVAL_LOUNGE_SIMULATION_FINISHED:
                simulationFinished = true;
                break;
        }
        response.setMessageType(MessageType.REPLY_OK);
        return response;
    }


    public boolean simulationFinished() {
        return simulationFinished;
    }


}
