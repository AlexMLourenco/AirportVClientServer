package sharedRegions;

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

    /** DONE */
    public synchronized void cleanUp() {
        this.numberOfPassengers = 0;
    }

    /** DONE */
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
            System.out.println("Unblocked Go Home");
        }
    }

    /** DONE */
    public synchronized int getNumberOfPassengers(){
        return numberOfPassengers;
    }

    /** DONE */
    public synchronized void readyToLeave() {
        notifyAll();
    }
}
