package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;
import mainProject.SimulPar;
import stubs.DepartureTerminalEntranceStub;
import stubs.RepositoryStub;

public class ArrivalTerminalExit implements SharedRegionInterface {

    private RepositoryStub repositoryStub;
    private DepartureTerminalEntranceStub departureTerminalEntranceStub;

    private int numberOfPassengers;

    public ArrivalTerminalExit(RepositoryStub repositoryStub,
                               DepartureTerminalEntranceStub departureTerminalEntranceStub){
        this.repositoryStub = repositoryStub;
        this.departureTerminalEntranceStub = departureTerminalEntranceStub;
    }


    public synchronized void cleanUp() {
        this.numberOfPassengers = 0;
    }

    public synchronized void goHome(int id){
        repositoryStub.setPassengerState(id, PassengerStates.EXITING_THE_ARRIVAL_TERMINAL);
        numberOfPassengers++;
        if (departureTerminalEntranceStub.getNumberOfPassengers() + numberOfPassengers == SimulPar.PASSENGERS) {
            notifyAll();
            departureTerminalEntranceStub.readyToLeave();
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
     * Wake the passengers when when everyone is ready to leave the airport or check in for the next leg of the journey
     *
     */
    public synchronized void readyToLeave() {
        notifyAll();
    }
}
