/**
 * <h1>Arrival Terminal Exit Stub </h1>
 * ArrivalTerminalExitStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;

public class ArrivalTerminalExitStub extends GenericStub {

    /**
     * ArrivalTerminalExitStub instatiation
     * @param hostname
     * @param port
     *
     */
    public ArrivalTerminalExitStub(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * sets the message to passenger go Home
     * @param identifier passenger id
     */
    public void goHome(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_EXIT_GO_HOME);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }
    /**
     * cleans up the arrival terminal Exit Stub when all the passengers are ready to goHome()
     */
    public void clean_up() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_EXIT_CLEAN_UP);

        this.process(outMessage);
    }
    /**
     * returns the namber of passengers in the Arrival Terminal Exit shared region
     */
    public int getNumberOfPassengers() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_EXIT_GET_NUMBER_OF_PASSENGERS);

        inMessage = this.process(outMessage);

        return inMessage.getIntValue();

    }

    /**
     * the passenger is ready to leave the airport
     */
    public void readyToLeave() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_EXIT_READY_TO_LEAVE);

        this.process(outMessage);
    }
    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_SIMULATION_FINISHED);

        this.process(outMessage);
    }

}
