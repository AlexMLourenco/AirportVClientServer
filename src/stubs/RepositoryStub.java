/**
 * <h1>Repository Stub </h1>
 * RepositoryStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
import entities.BusDriverStates;
import entities.PassengerStates;
import entities.PorterStates;
import mainProject.SharedRegionConfig;

public class RepositoryStub extends GenericStub{
    /**
     * RepositoryStub instatiation
     */
    public RepositoryStub() {
        super(SharedRegionConfig.SERVER_REPOSITORY_HOSTNAME,SharedRegionConfig.SERVER_REPOSITORY_PORT);
    }
    /**
     * RepositoryStub instatiation
     * @param port
     * @param hostname
     */
    public RepositoryStub(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * Porter removes the luggage in the plain hold
     */
    public void removeLuggageInPlainHold() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REMOVE_LUGGAGE_IN_PLANE_HOLD);

        this.process(outMessage);
    }

    /**
     * Initiates the repository for a flight
     * @param flightNumber int that identifies the flight which repository will be initiated
     */
    public void init_repository(int flightNumber) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_INIT);
        outMessage.setIntValue(flightNumber);

        this.process(outMessage);
    }
    /**
     * Flight lands at the airport
     * @param flightNumber int that identifies the flight that landed
     */
    public void flightLanded(int flightNumber) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_FLIGHT_LANDED);
        outMessage.setIntValue(flightNumber);

        this.process(outMessage);
    }

    /**
     * Passenger arrives the airport
     * @param id identifies the passenger
     * @param isFinalDestination sets if its the final destination of the passenger
     * @param numberOfLuggages that passenger brought
     * @return the next Action that passenger will take, 'H' go Home, 'C' Collect Bag , 'B' Take the bus
     */
    public char passengerArrived(int id, boolean isFinalDestination, int numberOfLuggages) {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_PASSENGER_ARRIVED);
        outMessage.setIdentifier(id);
        outMessage.setBooleanValue(isFinalDestination);
        outMessage.setIntValue(numberOfLuggages);

        inMessage = this.process(outMessage);
        return inMessage.getCharValue();
    }

    /**
     * Sets the passenger state
     * @param identifier identifies the passenger which state will be updated
     * @param state new state
     */
    public void setPassengerState(int identifier, PassengerStates state) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_SET_PASSENGER_STATE);
        outMessage.setIdentifier(identifier);
        outMessage.setEntityState(state);
        this.process(outMessage);
    }

    /**
     * Register passenger to take the bus
     * @param identifier identifies the passenger that needs to take the bus
     */
    public void registerPassengerToTakeABus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_PASSENGER_TO_TAKE_A_BUS);
        outMessage.setIdentifier(identifier);
        this.process(outMessage);

    }
    /**
     * Register passenger to enter the bus
     * @param identifier identifies the passenger that will enter the bus
     */
    public void registerPassengerToEnterTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_PASSENGER_TO_ENTER_THE_BUS);
        outMessage.setIdentifier(identifier);
        this.process(outMessage);

    }

    /**
     * Updates the Bus Driver state
     * @param state the new state of the driver
     */
    public void setBusDriverState(BusDriverStates state) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_SET_BUS_DRIVER_STATE);
        outMessage.setEntityState(state);
        this.process(outMessage);

    }

    /**
     * register Luggage in Conveyor Belt
     */
    public void registerLuggageInConveyorBelt() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_LUGGAGE_IN_CONVEYOR_BELT);
        this.process(outMessage);
    }
    /**
     * Updates the Port state
     * @param state the new state of Porter
     */
    public void setPorterState(PorterStates state) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_SET_PORTER_STATE);
        outMessage.setEntityState(state);
        this.process(outMessage);
    }
    /**
     * register collected Luggage
     * @param identifier identifies the bag and the passenger as well
     */
    public void registerCollectedLuggage(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_COLLECTED_LUGGAGE);
        outMessage.setIdentifier(identifier);
        this.process(outMessage);

    }

    /**
     * removes passenger from the bus
     * @param identifier identifies the passenger to be removed from bus
     */
    public void removePassengerFromTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REMOVE_PASSENGER_FROM_BUS);
        outMessage.setIdentifier(identifier);
        this.process(outMessage);
    }
    /**
     * register Luggage In Store Room
     */
    public void registerLuggageInStoreRoom() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_LUGGAGE_IN_STORE_ROOM);
        this.process(outMessage);
    }

    /**
     * Sets final stats
     */
    public void setFinalStats() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_FINAL_STATS);
        this.process(outMessage);
    }
    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_SIMULATION_FINISHED);
        this.process(outMessage);
    }

}
