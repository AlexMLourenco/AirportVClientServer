package stubs;

import commonInfra.Message;
import commonInfra.MessageType;

public class DepartureTerminalTransferQuayStub extends GenericStub{

    public DepartureTerminalTransferQuayStub(String hostname, int port) {
        super(hostname, port);
    }

    public void parkTheBusAndLetPassOff(int passengersOnTheBus) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS_AND_LET_PASS_OFF);
        outMessage.setIntValue(passengersOnTheBus);

        this.process(outMessage);
    }

    public void goToArrivalTerminal() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_TRANSFER_QUAY_GO_TO_ARRIVAL_TERMINAL);

        this.process(outMessage);

    }

    public void leaveTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_TRANSFER_QUAY_LEAVE_THE_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);

    }
}
