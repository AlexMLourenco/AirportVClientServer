package stubs;

import common.Message;
import common.MessageType;
import entities.Porter;

public class ArrivalTerminalTransferQuayStub extends GenericStub{

    public ArrivalTerminalTransferQuayStub(String hostname, int port) {
        super(hostname, port);
    }

    public boolean readyToDeparture() {
        return true;
    }

    public void announcingBusBoarding() {
    }

    public void goToDepartureTerminal() {
    }

    public void parkTheBus() {
    }

    public void takeABus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_TAKE_A_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }
    public void setBusDriverEndOfWork() {

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
