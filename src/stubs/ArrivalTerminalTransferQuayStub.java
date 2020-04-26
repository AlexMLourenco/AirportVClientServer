package stubs;

import commonInfra.Message;
import commonInfra.MessageType;

public class ArrivalTerminalTransferQuayStub extends GenericStub{

    public ArrivalTerminalTransferQuayStub(String hostname, int port) {
        super(hostname, port);
    }

    public boolean readyToDeparture() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_READY_TO_DEPARTUE);

        this.process(outMessage);

        return true;
    }

    public int announcingBusBoarding() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_ANNOUNCING_BUS_BOARDING);

        inMessage = this.process(outMessage);
        return inMessage.getIntValue();
    }

    public void goToDepartureTerminal() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_GO_TO_DEPARTURE_TERMINAL);

        this.process(outMessage);

    }

    public void parkTheBus() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS);

        this.process(outMessage);
    }

    public void takeABus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_TAKE_A_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }
    public void setBusDriverEndOfWork() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_SET_BUS_DRIVER_END_OF_WORK);

        this.process(outMessage);

    }
    public void enterTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_ENTER_THE_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }

    public void waitForBus(int identifier){
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_WAIT_FOR_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }
}
