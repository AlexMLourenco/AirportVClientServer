/**
 * <h1>Arrival Lounge Stub </h1>
 * ArrivalLoungeStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
import commonInfra.BAG;
/**
 * This stub class represents the Arrival Lounge as it is needed in the
 * client side and communicates with the server side.
 */
public class ArrivalLoungeStub extends GenericStub{
    /**
     * ArrivalLoungeStub instatiation
     * @param hostname server hostname
     * @param port     server port
    */
    public ArrivalLoungeStub(String hostname, int port) {
        super(hostname, port);
    }
    /**
     * sets the message to set the end of work for porter
     */
    public void setPorterEndOfWork() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_SET_PORTER_END_OF_WORK);

        this.process(outMessage);
    }

    /**
     * sets the message to porter take a rest
     */
    public void takeARest() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_TAKE_A_REST);

        this.process(outMessage);
    }
    /**
     * sets the message to initiate the plane hold
     * @param flightNumber int that identifies de flight
     * @param plainHoldLuggage array that contains the luggage info
     * @param passengersFinalDestination boolean array that contains info about passengers final destination
     */
    public void init_plane_hold(int flightNumber, int [][] plainHoldLuggage, boolean [][] passengersFinalDestination ) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_INIT_PLANE_HOLD);
        outMessage.setIntValue(flightNumber);
        outMessage.setIntArray(plainHoldLuggage);
        outMessage.setBooleanArray(passengersFinalDestination);

        this.process(outMessage);
    }
    /**
     * returns the message to indicate if there's more bags to collect or not
     * @return Message {@code true} if there's more bags to collect otherwise {@code @false}
     * * */
    public Boolean noMoreBagsToCollect() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_NO_MORE_BAGS_TO_COLLECT);

        inMessage = this.process(outMessage);

        return inMessage.getBooleanValue();
    }
    /**
     * returns message with the bag collected
     * @return a message with the bag collected
     * */
    public BAG tryToCollectABag() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_TRY_TO_COLLECT_A_BAG);

        inMessage = this.process(outMessage);

        return inMessage.getBag();
    }
    /**
     * returns message with the whatShoulIDo char
     * @param identifier passenger identifier
     * @param isFinalDestination boolean value to check if its passenger final destination
     * @param numberOfLuggages int number of luggages of the passenger
     * @return a message with the char that represents the next action of the passenger 'B' | 'C' | 'H'
     * */
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

    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_LOUNGE_SIMULATION_FINISHED);

        this.process(outMessage);
    }
}
