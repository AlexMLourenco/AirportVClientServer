package sharedRegions;

import entities.PassengerStates;
import mainProject.SimulPar;
import stubs.ArrivalTerminalExitStub;
import stubs.RepositoryStub;

public class DepartureTerminalEntrance implements SharedRegionInterface {

    private RepositoryStub repositoryStub;
    private ArrivalTerminalExitStub arrivalTerminalExitStub;

    private int numberOfPassengers = 0;

    public DepartureTerminalEntrance(RepositoryStub repositoryStub,
                                     ArrivalTerminalExitStub arrivalTerminalExitStub){
        this.repositoryStub = repositoryStub;
        this.arrivalTerminalExitStub = arrivalTerminalExitStub;
    }

    /*****  PASSENGER  FUNCTIONS *****/

    /** DONE **/
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
        }
    }


    /** DONE **/
    public synchronized int getNumberOfPassengers(){
        return numberOfPassengers;
    }


    /** DONE **/
    public synchronized void readyToLeave() {
        notifyAll();
    }

    /** DONE **/
    public synchronized void cleanUp() {
        this.numberOfPassengers = 0;
    }
}
