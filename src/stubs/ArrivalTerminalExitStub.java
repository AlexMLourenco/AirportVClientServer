package stubs;

import commonInfra.Message;
import commonInfra.MessageType;

public class ArrivalTerminalExitStub extends GenericStub {

    public ArrivalTerminalExitStub(String hostname, int port) {
        super(hostname, port);
    }

    public void goHome(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_EXIT_GO_HOME);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }

    public void clean_up() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_EXIT_CLEAN_UP);

        this.process(outMessage);
    }

    public int getNumberOfPassengers() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_EXIT_GET_NUMBER_OF_PASSENGERS);

        inMessage = this.process(outMessage);

        return inMessage.getIntValue();

    }

    public void readyToLeave() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_EXIT_READY_TO_LEAVE);

        this.process(outMessage);
    }

    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_SIMULATION_FINISHED);

        this.process(outMessage);
    }

}
