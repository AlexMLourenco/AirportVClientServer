/**
 * <h1>Departure Terminal Transfer Quay Stub </h1>
 * DepartureTerminalTransferQuayStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
/**
 * This stub class represents the Departure Terminal TransferQuay as it is needed in the
 * client side and communicates with the server side.
 */
public class DepartureTerminalTransferQuayStub extends GenericStub{
    /**
     * DepartureTerminalTransferQuayStub instatiation
     * @param hostname server hostname
     * @param port     server port
     */
    public DepartureTerminalTransferQuayStub(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * Driver parks the bus and let all the passengers leave the bus
     * @param passengersOnTheBus int that represents the number of passengers on the bus
     */
    public void parkTheBusAndLetPassOff(int passengersOnTheBus) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS_AND_LET_PASS_OFF);
        outMessage.setIntValue(passengersOnTheBus);

        this.process(outMessage);
    }
    /**
     * Driver leaves the Departure terminal after all passengers left the bus
     */
    public void goToArrivalTerminal() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_TRANSFER_QUAY_GO_TO_ARRIVAL_TERMINAL);

        this.process(outMessage);

    }
    /**
     * Passenger leaves the Bus after bus is parked
     * @param identifier int that identifies the passenger leaving the bus
     */
    public void leaveTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_TRANSFER_QUAY_LEAVE_THE_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);

    }

    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.DEPARTURE_TERMINAL_TRANSFER_QUAY_SIMULATION_FINISHED);

        this.process(outMessage);
    }
}
