package stubs;

import common.Message;
import common.MessageType;

public class DepartureTerminalTransferQuayStub extends GenericStub{

    public DepartureTerminalTransferQuayStub(String hostname, int port) {
        super(hostname, port);
    }

    public void parkTheBusAndLetPassOff() {
    }

    public void goToArrivalTerminal() {

    }

    public void leaveTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_TRANSFER_QUAY_LEAVE_THE_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);

    }
}
