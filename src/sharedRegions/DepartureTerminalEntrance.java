/**
 * <h1>Departure Terminal Entrance </h1>
 * DepartureTerminalEntrance Class implements the SharedRegionInterface and Departure Terminal Entrance shared memory region.
 * In this shared region, is where the passenger goes to get the next leg
 *
 */
package sharedRegions;

import entities.PassengerStates;
import mainProject.SimulPar;
import stubs.ArrivalTerminalExitStub;
import stubs.RepositoryStub;

public class DepartureTerminalEntrance implements SharedRegionInterface {

    private RepositoryStub repositoryStub;
    private ArrivalTerminalExitStub arrivalTerminalExitStub;

    private int numberOfPassengers = 0;
    /**
     * DepartureTerminalEntrance constructor.
     * Creates a DepartureTerminalEntrance in repository Stub
     * @param repositoryStub that corresponds to Stub Repository
     * @param arrivalTerminalExitStub corresponds to Departure terminal entrance stub
     */
    public DepartureTerminalEntrance(RepositoryStub repositoryStub,
                                     ArrivalTerminalExitStub arrivalTerminalExitStub){
        this.repositoryStub = repositoryStub;
        this.arrivalTerminalExitStub = arrivalTerminalExitStub;
    }

    /*****  PASSENGER  FUNCTIONS *****/

    /**
     * Passenger prepareNextLeg(int id) method
     * @param id Passenger identifier
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
        }
    }


    /**
     * Returns number of Passengers to get next Leg
     * @return number of Passengers to get next Leg
     */
    public synchronized int getNumberOfPassengers(){
        return numberOfPassengers;
    }

    /**
     * notifies that is ready to leave
     */
    public synchronized void readyToLeave() {
        notifyAll();
    }

    /**
     * set the number of passengers to 0
     */
    public synchronized void cleanUp() {
        this.numberOfPassengers = 0;
    }
}
