/**
 * <h1>Arrival Terminal Transfer Quay Stub </h1>
 * ArrivalTerminalTransferQuayStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;

public class ArrivalTerminalTransferQuayStub extends GenericStub{

    /**
     * ArrivalTerminalExitStub instatiation
     * @param hostname
     * @param port
     *
     */
    public ArrivalTerminalTransferQuayStub(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * checks if the bus is ready to leave arrival terminal
     * @return {@code true} it means that the bus is ready to go to departure terminal
     */
    public boolean readyToDeparture() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_READY_TO_DEPARTUE);

        this.process(outMessage);

        return true;
    }

    public int announcingBusBoarding() {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_ANNOUNCING_BUS_BOARDING);

        inMessage = this.process(outMessage);
        return inMessage.getIntValue();
    }
    /**
     * Bus going to departure terminal
     */
    public void goToDepartureTerminal() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_GO_TO_DEPARTURE_TERMINAL);

        this.process(outMessage);

    }
    /**
     * driver parks the bus in the arrival terminal transfer quay
     */
    public void parkTheBus() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS);

        this.process(outMessage);
    }
    /**
     * passenger takes the bus
     * @param identifier int that identifies the passenger that needs to take the bus
     */
    public void takeABus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_TAKE_A_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }
    /**
     * Bus Driver end of the work
     */
    public void setBusDriverEndOfWork() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_SET_BUS_DRIVER_END_OF_WORK);

        this.process(outMessage);

    }
    /**
     * passenger enters the bus
     * @param identifier int that identifies the passenger that enters the bus
     */
    public void enterTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_ENTER_THE_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }
    /**
     * passenger waits for the bus
     * @param identifier int that identifies the passenger that waits for the bus
     */
    public void waitForBus(int identifier){
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_WAIT_FOR_BUS);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);
    }

    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.ARRIVAL_TERMINAL_TRANSFER_QUAY_SIMULATION_FINISHED);

        this.process(outMessage);
    }
}
