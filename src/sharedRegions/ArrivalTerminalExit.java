package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class ArrivalTerminalExit implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    private int numberOfPassengers;

    private DepartureTerminalEntrance departureTerminalEntrance;

    public ArrivalTerminalExit(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
    }

    /***** PASSENGER FUNCTIONS *********/

    public synchronized void goHome(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repositoryStub.setPassengerState(passenger.getIdentifier(), PassengerStates.EXITING_THE_ARRIVAL_TERMINAL);
        numberOfPassengers++;
        if (departureTerminalEntrance.getNumberOfPassengers() + numberOfPassengers == SimulPar.PASSENGERS) {
            notifyAll();
            departureTerminalEntrance.readyToLeave();
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
     * Set the Departure Terminal Entrance memory zone
     *
     */
    public void setDepartureTerminalEntrance(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
    }

    /**
     * Wake the passengers when when everyone is ready to leave the airport or check in for the next leg of the journey
     *
     */
    public synchronized void readyToLeave() {
        notifyAll();
    }
}
