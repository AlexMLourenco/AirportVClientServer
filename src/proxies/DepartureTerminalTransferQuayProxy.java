package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.DepartureTerminalTransferQuay;

public class DepartureTerminalTransferQuayProxy implements SharedRegionProxyInterface {

    private final DepartureTerminalTransferQuay departureTerminalTransferQuay;

    public DepartureTerminalTransferQuayProxy(DepartureTerminalTransferQuay departureTerminalTransferQuay) {
        this.departureTerminalTransferQuay = departureTerminalTransferQuay;
    }
    public Message processAndReply(Message message) {
        Message response = new Message();
        System.out.println("Processing Message Type: " + message.getMessageType());
        switch(message.getMessageType()) {
            case DEPARTURE_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS_AND_LET_PASS_OFF:
                departureTerminalTransferQuay.parkTheBusAndLetPassOff(message.getIntValue());
                break;
            case DEPARTURE_TERMINAL_TRANSFER_QUAY_LEAVE_THE_BUS:
                departureTerminalTransferQuay.leaveTheBus(message.getIdentifier());
                break;
            case DEPARTURE_TERMINAL_TRANSFER_QUAY_GO_TO_ARRIVAL_TERMINAL:
                departureTerminalTransferQuay.goToArrivalTerminal();
                break;

        }
        response.setMessageType(MessageType.REPLY_OK);
        System.out.println("Replying Message: " + response);
        return response;
    }

    public boolean simulationFinished() {
        return false;
    }


}
