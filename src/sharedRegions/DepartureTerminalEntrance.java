package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class DepartureTerminalEntrance implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    private int numberOfPassengers;

    private ArrivalTerminalExit arrivalTerminalExit;

    public DepartureTerminalEntrance(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
    }

    /*****  PASSENGER  FUNCTIONS *****/

    /**
     * Passenger is in the departure terminal to prepare the next Leg of the journey
     *
     */
    public synchronized void prepareNextLeg(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repositoryStub.setPassengerState(passenger.getIdentifier(), PassengerStates.ENTERING_THE_DEPARTURE_TERMINAL);
        numberOfPassengers++;
        if (arrivalTerminalExit.getNumberOfPassengers() + numberOfPassengers == SimulPar.PASSENGERS) {
            notifyAll();
            arrivalTerminalExit.readyToLeave();
        } else{
            try {
                wait();
            } catch (InterruptedException e) {}
        }
    }


    /**
     * Get the number of passengers waiting for their fellow passengers
     *
     */
    public synchronized int getNumberOfPassengers(){
        return numberOfPassengers;
    }

    /**
     * Set the Arrival Terminal Exit memory zone
     *
     */
    public void setArrivalTerminalExit(ArrivalTerminalExit arrivalTerminalExit) {
        this.arrivalTerminalExit = arrivalTerminalExit;
    }

    /**
     * Wake the passengers when when everyone is ready to leave the airport or check in for the next leg of the journey
     *
     */
    public synchronized void readyToLeave() {
        notifyAll();
    }
}
