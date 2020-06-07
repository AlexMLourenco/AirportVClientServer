/**
 * <h1>Arrival Terminal Transfer Quay Proxy</h1>
 * The ArrivalTerminalTransferQuayProxy class implements SharedRegionProxyInterface methods
 * to proccess and reply the messages and checks if the simulation is finished
 * in Arrival Terminal Transfer Quay shared region
 /**
 * Package to process the messages
 */

package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.ArrivalTerminalTransferQuay;

public class ArrivalTerminalTransferQuayProxy implements SharedRegionProxyInterface {

    private final ArrivalTerminalTransferQuay arrivalTerminalTransferQuay;

    private boolean simulationFinished = false;

    /**
     * ArrivalTerminalTransferQuayProxy constructor.
     * Creates a ArrivalTerminalTransferQuayProxy in Arrival Terminal Transfer Quay  Shared Region
     * @param arrivalTerminalTransferQuay that corresponds to Arrival Terminal Transfer Quay Shared Region
     */

    public ArrivalTerminalTransferQuayProxy(ArrivalTerminalTransferQuay arrivalTerminalTransferQuay) {
        this.arrivalTerminalTransferQuay = arrivalTerminalTransferQuay;

    }

    public Message processAndReply(Message message) {
        Message response = new Message();
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

            case ARRIVAL_TERMINAL_TRANSFER_QUAY_SIMULATION_FINISHED:
                this.simulationFinished = true;
                break;
        }
        response.setMessageType(MessageType.REPLY_OK);
        return response;
    }

    public boolean simulationFinished() {
        return simulationFinished;
    }


}
