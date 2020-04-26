package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
import entities.BusDriverStates;
import entities.PassengerStates;
import entities.PorterStates;
import mainProject.SimulPar;

public class RepositoryStub extends GenericStub{

    public RepositoryStub() {
        super(SimulPar.SERVER_REPOSITORY_HOSTNAME,SimulPar.SERVER_REPOSITORY_PORT);
    }

    public RepositoryStub(String hostname, int port) {
        super(hostname, port);
    }


    public void removeLuggageInPlainHold() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REMOVE_LUGGAGE_IN_PLANE_HOLD);

        this.process(outMessage);
    }

    public void init_repository(int flightNumber) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_INIT);
        outMessage.setIntValue(flightNumber);

        this.process(outMessage);
    }

    public void flightLanded(int flightNumber) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_FLIGHT_LANDED);
        outMessage.setIntValue(flightNumber);

        this.process(outMessage);
    }

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

    public void setPassengerState(int identifier, PassengerStates state) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_SET_PASSENGER_STATE);
        outMessage.setIdentifier(identifier);
        outMessage.setEntityState(state);
        this.process(outMessage);
    }

    public void registerPassengerToTakeABus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_PASSENGER_TO_TAKE_A_BUS);
        outMessage.setIdentifier(identifier);
        this.process(outMessage);

    }

    public void registerPassengerToEnterTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_PASSENGER_TO_ENTER_THE_BUS);
        outMessage.setIdentifier(identifier);
        this.process(outMessage);

    }

    public void setBusDriverState(BusDriverStates state) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_SET_BUS_DRIVER_STATE);
        outMessage.setEntityState(state);
        this.process(outMessage);

    }

    public void registerLuggageInConveyorBelt() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_LUGGAGE_IN_CONVEYOR_BELT);
        this.process(outMessage);
    }

    public void setPorterState(PorterStates state) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_SET_PORTER_STATE);
        outMessage.setEntityState(state);
        this.process(outMessage);
    }

    public void registerCollectedLuggage(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_COLLECTED_LUGGAGE);
        outMessage.setIdentifier(identifier);
        this.process(outMessage);

    }

    public void removePassengerFromTheBus(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REMOVE_PASSENGER_FROM_BUS);
        outMessage.setIdentifier(identifier);
        this.process(outMessage);
    }

    public void registerLuggageInStoreRoom() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REGISTER_LUGGAGE_IN_STORE_ROOM);
        this.process(outMessage);
    }
}
