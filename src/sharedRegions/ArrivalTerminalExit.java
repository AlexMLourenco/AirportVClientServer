/**
 * <h1>Arrival Terminal Exit</h1>
 * ArrivalTerminalExit Class implements SharedRegionInterface and the Arrival Terminal Exit shared memory region.
 * In this shared region, is where the passengers leave the airport
 * if its their final destination
 *
 */

package sharedRegions;

import entities.PassengerStates;
import mainProject.SimulPar;
import stubs.DepartureTerminalEntranceStub;
import stubs.RepositoryStub;

public class ArrivalTerminalExit implements SharedRegionInterface {

    private RepositoryStub repositoryStub;
    private DepartureTerminalEntranceStub departureTerminalEntranceStub;

    private int numberOfPassengers;
    /**
     * ArrivalTerminalExit constructor.
     * Creates a ArrivalTerminalExit in specified repository Stub and departure Terminal Entrance Stub
     * @param repositoryStub that corresponds to Stub Repository
     * @param departureTerminalEntranceStub that corresponds to departure Terminal Entrance Stub
     */
    public ArrivalTerminalExit(RepositoryStub repositoryStub,
                               DepartureTerminalEntranceStub departureTerminalEntranceStub){
        this.repositoryStub = repositoryStub;
        this.departureTerminalEntranceStub = departureTerminalEntranceStub;
    }

    /**
     * sets the number of passengers to 0
     * */
    public synchronized void cleanUp() {
        this.numberOfPassengers = 0;
    }

    /**
     * Passenger leaves the airport when reaches its final destination
     * @param id The id of the passenger
     * */
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
     * Returns the number of Passengers
     * @return numberOfPassengers Returns the number of Passengers
     * */
    public synchronized int getNumberOfPassengers(){
        return numberOfPassengers;
    }

    /**
     * Passanger notifies that is ready to leave the airport
     * */
    public synchronized void readyToLeave() {
        notifyAll();
    }
}
