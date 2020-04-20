package stubs;

import common.Message;
import common.MessageType;
import commonInfra.BAG;
import entities.Porter;

public class ArrivalLoungeStub extends GenericStub{


    public ArrivalLoungeStub(String hostname, int port) {
        super(hostname, port);
    }

    public void setPorterEndOfWork() {

    }
    public void takeARest() {
        Message outMessage, inMessage;
        Porter porter = (Porter) Thread.currentThread();

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_TAKE_A_REST);

        inMessage = this.process(outMessage);

        porter.setEntityState(inMessage.getEntityState());
    }

    public void init_plane_hold(int flightNumber) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_INIT_PLANE_HOLD);
        outMessage.setIntValue(flightNumber);

        this.process(outMessage);
    }

    public Boolean noMoreBagsToCollect() {
        return  true;
    }

    public BAG tryToCollectABag() {
        return null;
    }

    public char whatShouldIDo(int identifier, boolean isFinalDestination, int numberOfLuggages) {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_WHAT_SHOULD_I_DO);
        outMessage.setIdentifier(identifier);
        outMessage.setBooleanValue(isFinalDestination);
        outMessage.setIntValue(numberOfLuggages);

        inMessage = this.process(outMessage);

        return inMessage.getCharValue();
    }
}
