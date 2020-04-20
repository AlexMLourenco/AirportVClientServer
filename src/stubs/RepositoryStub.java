package stubs;

import common.Message;
import common.MessageType;
import entities.BusDriverStates;
import entities.PassengerStates;
import entities.Porter;
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
        Message outMessage, inMessage;
        Porter porter = (Porter) Thread.currentThread();

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_REMOVE_LUGGAGE_IN_PLANE_HOLD);

        inMessage = this.process(outMessage);

        porter.setEntityState(inMessage.getEntityState());
    }

    public void init_repository(int flightNumber) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.REPOSITORY_INIT);
        outMessage.setIntValue(flightNumber);

        this.process(outMessage);
    }

    public void flightLanded(int size) {
    }

    public synchronized char passengerArrived(int id, boolean isFinalDestination, int numberOfLuggages) {
return 'C';
    }

    public void setPassengerState(int identifier, PassengerStates state) {
    }

    public void registerPassengerToTakeABus(int id) {
    }

    public void registerPassengerToEnterTheBus(int id) {
    }

    public void setBusDriverState(BusDriverStates state) {
    }

    public void registerLuggageInConveyorBelt() {
    }

    public void setPorterState(PorterStates state) {
    }

    public void registerCollectedLuggage(int passenger) {
    }

    public void removePassengerFromTheBus(int id) {
    }

    public void registerLuggageInStoreRoom() {
    }
}
