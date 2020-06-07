/**
 * <h1>Departure Terminal Entrance Stub </h1>
 * DepartureTerminalEntranceStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;

public class DepartureTerminalEntranceStub  extends GenericStub{
    /**
     * DepartureTerminalEntranceStub instatiation
     * @param hostname
     * @param port
     *
     */
    public DepartureTerminalEntranceStub(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * Passengers arrive the departure terminal
     * @param identifier identifies the passenger that arrived
     */
    public void prepareNextLeg(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_PREPARE_NEXT_LEG);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }

    /**
     * returns the number of passengers on the departure terminal
     * @return number of passengers on the departure terminal
     */
    public int getNumberOfPassengers() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_GET_NUMBER_OF_PASSENGERS);

        inMessage = this.process(outMessage);

        return inMessage.getIntValue();
    }
    /**
     * Passengers ready to leave the departure terminal
     */
    public void readyToLeave() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_READY_TO_LEAVE);

        this.process(outMessage);
    }
    /**
     * No Passengers on the departure terminal
     */
    public void clean_up() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_CLEAN_UP);

        this.process(outMessage);
    }
    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_SIMULATION_FINISHED);

        this.process(outMessage);
    }
}

