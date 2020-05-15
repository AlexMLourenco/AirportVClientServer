package sharedRegions;

import entities.PassengerStates;
import mainProject.SimulPar;
import stubs.ArrivalTerminalExitStub;
import stubs.RepositoryStub;

public class DepartureTerminalEntrance implements SharedRegionInterface {

    private RepositoryStub repositoryStub;
    private ArrivalTerminalExitStub arrivalTerminalExitStub;

    private int numberOfPassengers;

    public DepartureTerminalEntrance(RepositoryStub repositoryStub,
                                     ArrivalTerminalExitStub arrivalTerminalExitStub){
        this.repositoryStub = repositoryStub;
        this.arrivalTerminalExitStub = arrivalTerminalExitStub;
    }

    /*****  PASSENGER  FUNCTIONS *****/

    /**
     * Passenger is in the departure terminal to prepare the next Leg of the journey
     *
     */
    public synchronized void prepareNextLeg(int id){
        repositoryStub.setPassengerState(id, PassengerStates.ENTERING_THE_DEPARTURE_TERMINAL);
        numberOfPassengers++;
        if (arrivalTerminalExitStub.getNumberOfPassengers() + numberOfPassengers == SimulPar.PASSENGERS) {
            notifyAll();
            arrivalTerminalExitStub.readyToLeave();
        } else{
            try {
                wait();
            } catch (InterruptedException e) {}
            System.out.println("Unblocked Prepare Next Leg");
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
     * Wake the passengers when when everyone is ready to leave the airport or check in for the next leg of the journey
     *
     */
    public synchronized void readyToLeave() {
        notifyAll();
    }

    public synchronized void cleanUp() {
        this.numberOfPassengers = 0;
    }
}
