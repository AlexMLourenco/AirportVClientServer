package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.ArrivalTerminalTransferQuay;

public class ArrivalTerminalTransferQuayProxy implements SharedRegionProxyInterface {

    private final ArrivalTerminalTransferQuay arrivalTerminalTransferQuay;

    public ArrivalTerminalTransferQuayProxy(ArrivalTerminalTransferQuay arrivalTerminalTransferQuay) {
        this.arrivalTerminalTransferQuay = arrivalTerminalTransferQuay;

    }
    public Message processAndReply(Message message) {
        Message response = new Message();
        System.out.println("Processing Message Type: " + message.getMessageType());
        switch(message.getMessageType()) {
            case ARRIVAL_TERMINAL_TRANSFER_QUAY_READY_TO_DEPARTUE:
                arrivalTerminalTransferQuay.readyToDeparture();
                break;
            case ARRIVAL_TERMINAL_TRANSFER_QUAY_ANNOUNCING_BUS_BOARDING:
                response.setIntValue(arrivalTerminalTransferQuay.announcingBusBoarding());
                break;
            case ARRIVAL_TERMINAL_TRANSFER_QUAY_GO_TO_DEPARTURE_TERMINAL:
                arrivalTerminalTransferQuay.goToDepartureTerminal();
                break;
            case ARRIVAL_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS:
                arrivalTerminalTransferQuay.parkTheBus();
                break;
            case ARRIVAL_TERMINAL_TRANSFER_QUAY_SET_BUS_DRIVER_END_OF_WORK:
                arrivalTerminalTransferQuay.setBusDriverEndOfWork();
                break;
            case ARRIVAL_TERMINAL_TRANSFER_QUAY_TAKE_A_BUS:
                arrivalTerminalTransferQuay.takeABus(message.getIdentifier());
                break;
            case ARRIVAL_TERMINAL_TRANSFER_QUAY_WAIT_FOR_BUS:
                arrivalTerminalTransferQuay.waitForBus(message.getIdentifier());
                break;
            case ARRIVAL_TERMINAL_TRANSFER_QUAY_ENTER_THE_BUS:
                arrivalTerminalTransferQuay.enterTheBus(message.getIdentifier());
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
