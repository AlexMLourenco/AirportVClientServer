package proxies;

import commonInfra.Message;
import commonInfra.MessageType;
import entities.BusDriverStates;
import entities.PassengerStates;
import entities.PorterStates;
import sharedRegions.Repository;

public class RepositoryProxy implements SharedRegionProxyInterface {

    private final Repository repository;

    private boolean simulationFinished = false;

    public RepositoryProxy(Repository repository) {
        this.repository = repository;
    }

    public Message processAndReply(Message message) {
        Message response = new Message();
        switch(message.getMessageType()) {
            case REPOSITORY_INIT: repository.init_repository(message.getIntValue());break;
            case REPOSITORY_PASSENGER_ARRIVED: response.setCharValue(repository.passengerArrived(message.getIdentifier(), message.getBooleanValue(), message.getIntValue()));
            case REPOSITORY_REMOVE_PASSENGER_FROM_BUS: repository.removePassengerFromTheBus(message.getIdentifier());break;
            case REPOSITORY_REGISTER_PASSENGER_TO_ENTER_THE_BUS: repository.registerPassengerToEnterTheBus(message.getIdentifier()); break;
            case REPOSITORY_REGISTER_PASSENGER_TO_TAKE_A_BUS: repository.registerPassengerToTakeABus(message.getIdentifier()); break;
            case REPOSITORY_REGISTER_COLLECTED_LUGGAGE: repository.registerCollectedLuggage(message.getIdentifier()); break;
            case REPOSITORY_REGISTER_LUGGAGE_IN_STORE_ROOM: repository.registerLuggageInStoreRoom(); break;
            case REPOSITORY_REGISTER_LUGGAGE_IN_CONVEYOR_BELT: repository.registerLuggageInConveyorBelt(); break;
            case REPOSITORY_REMOVE_LUGGAGE_IN_PLANE_HOLD: repository.removeLuggageInPlainHold(); break;
            case REPOSITORY_FLIGHT_LANDED: repository.flightLanded(message.getIntValue()); break;
            case REPOSITORY_SET_PORTER_STATE: repository.setPorterState((PorterStates)message.getEntityState()); break;
            case REPOSITORY_SET_BUS_DRIVER_STATE: repository.setBusDriverState((BusDriverStates)message.getEntityState());break;
            case REPOSITORY_SET_PASSENGER_STATE: repository.setPassengerState(message.getIdentifier(), (PassengerStates)message.getEntityState());break;
            case REPOSITORY_SIMULATION_FINISHED: this.simulationFinished = true ; break;
        }
        response.setMessageType(MessageType.REPLY_OK);
        return response;
    }

    public boolean simulationFinished() {
        return simulationFinished;
    }


}
