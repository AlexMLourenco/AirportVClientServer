package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.DepartureTerminalEntrance;

public class DepartureTerminalEntranceProxy implements SharedRegionProxyInterface {

    private final DepartureTerminalEntrance departureTerminalEntrance;

    private boolean simulationFinished = false;


    public DepartureTerminalEntranceProxy(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
    }

    public Message processAndReply(Message message) {

        Message response = new Message();
        System.out.println("Processing Message Type: " + message.getMessageType());

        switch(message.getMessageType()) {
            case DEPARTURE_TERMINAL_ENTRANCE_PREPARE_NEXT_LEG:
                departureTerminalEntrance.prepareNextLeg(message.getIdentifier());
                break;
            case DEPARTURE_TERMINAL_ENTRANCE_GET_NUMBER_OF_PASSENGERS:
                response.setIntValue(departureTerminalEntrance.getNumberOfPassengers());
                break;
            case DEPARTURE_TERMINAL_ENTRANCE_READY_TO_LEAVE:
                departureTerminalEntrance.readyToLeave();
                break;
            case DEPARTURE_TERMINAL_ENTRANCE_CLEAN_UP:
                departureTerminalEntrance.cleanUp();
                break;
            case DEPARTURE_TERMINAL_ENTRANCE_SIMULATION_FINISHED:
                this.simulationFinished = true;
                break;
        }
        response.setMessageType(MessageType.REPLY_OK);
        System.out.println("Replying Message: " + response);
        return response;
    }

    public boolean simulationFinished() {
        return this.simulationFinished;
    }


}
