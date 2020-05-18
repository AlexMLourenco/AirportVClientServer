package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
import commonInfra.BAG;

public class ArrivalLoungeStub extends GenericStub{


    public ArrivalLoungeStub(String hostname, int port) {
        super(hostname, port);
    }

    public void setPorterEndOfWork() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_SET_PORTER_END_OF_WORK);

        this.process(outMessage);
    }

    public void takeARest() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_TAKE_A_REST);

        this.process(outMessage);
    }

    public void init_plane_hold(int flightNumber, int [][] plainHoldLuggage, boolean [][] passengersFinalDestination ) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_INIT_PLANE_HOLD);
        outMessage.setIntValue(flightNumber);
        outMessage.setIntArray(plainHoldLuggage);
        outMessage.setBooleanArray(passengersFinalDestination);

        this.process(outMessage);
    }

    public Boolean noMoreBagsToCollect() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_NO_MORE_BAGS_TO_COLLECT);

        inMessage = this.process(outMessage);

        return inMessage.getBooleanValue();
    }

    public BAG tryToCollectABag() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_TRY_TO_COLLECT_A_BAG);

        inMessage = this.process(outMessage);

        return inMessage.getBag();
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

    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_SIMULATION_FINISHED);

        this.process(outMessage);
    }
}
