package stubs;

import commonInfra.Message;
import commonInfra.MessageType;

public class DepartureTerminalEntranceStub  extends GenericStub{

    public DepartureTerminalEntranceStub(String hostname, int port) {
        super(hostname, port);
    }

    public void prepareNextLeg(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_PREPARE_NEXT_LEG);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }

    public int getNumberOfPassengers() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_GET_NUMBER_OF_PASSENGERS);

        inMessage = this.process(outMessage);

        return inMessage.getIntValue();
    }

    public void readyToLeave() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_READY_TO_LEAVE);

        this.process(outMessage);
    }

    public void clean_up() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_CLEAN_UP);

        this.process(outMessage);
    }

    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRANCE_SIMULATION_FINISHED);

        this.process(outMessage);
    }
}

