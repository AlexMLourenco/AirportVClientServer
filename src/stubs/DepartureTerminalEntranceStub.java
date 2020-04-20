package stubs;

import common.Message;
import common.MessageType;

public class DepartureTerminalEntranceStub  extends GenericStub{

    public DepartureTerminalEntranceStub(String hostname, int port) {
        super(hostname, port);
    }

    public void prepareNextLeg(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_ENTRACE_PREPARE_NEXT_LEG);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }

    public void clean_up() {

    }
}

