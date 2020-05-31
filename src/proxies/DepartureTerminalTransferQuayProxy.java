package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.DepartureTerminalTransferQuay;

public class DepartureTerminalTransferQuayProxy implements SharedRegionProxyInterface {

    private final DepartureTerminalTransferQuay departureTerminalTransferQuay;
    private boolean simulationFinished = false;
    public DepartureTerminalTransferQuayProxy(DepartureTerminalTransferQuay departureTerminalTransferQuay) {
        this.departureTerminalTransferQuay = departureTerminalTransferQuay;
    }
    public Message processAndReply(Message message) {
        Message response = new Message();
        switch(message.getMessageType()) {
            case DEPARTURE_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS_AND_LET_PASS_OFF:
                departureTerminalTransferQuay.parkTheBusAndLetPassOff(message.getIntValue());
                break;
            case DEPARTURE_TERMINAL_TRANSFER_QUAY_LEAVE_THE_BUS:
                System.out.println("IN: " + message.getIdentifier());
                departureTerminalTransferQuay.leaveTheBus(message.getIdentifier());
                break;
            case DEPARTURE_TERMINAL_TRANSFER_QUAY_GO_TO_ARRIVAL_TERMINAL:
                departureTerminalTransferQuay.goToArrivalTerminal();
                break;
            case DEPARTURE_TERMINAL_TRANSFER_QUAY_SIMULATION_FINISHED:
                this.simulationFinished = true;
                break;
        }
        response.setMessageType(MessageType.REPLY_OK);
        return response;
    }

    public boolean simulationFinished() {
        return this.simulationFinished;
    }


}
