/**
 * <h1>Departure Terminal Entrance Proxy</h1>
 * The DepartureTerminalEntranceProxy class implements SharedRegionProxyInterface methods
 * to proccess and reply the messages and checks if the simulation is finished
 * in Departure Terminal Entrance shared region
 */
package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import sharedRegions.DepartureTerminalEntrance;

public class DepartureTerminalEntranceProxy implements SharedRegionProxyInterface {

    private final DepartureTerminalEntrance departureTerminalEntrance;

    private boolean simulationFinished = false;

    /**
     * DepartureTerminalEntranceProxy constructor.
     * Creates a DepartureTerminalEntranceProxy in Departure Terminal Entrance Shared Region
     * @param departureTerminalEntrance that corresponds to Departure Terminal Entrance Shared Region
     */
    public DepartureTerminalEntranceProxy(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
    }

    public Message processAndReply(Message message) {

        Message response = new Message();

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
        return response;
    }

    public boolean simulationFinished() {
        return this.simulationFinished;
    }


}
