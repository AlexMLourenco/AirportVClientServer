/**
 * <h1>Departure Terminal Transfer Quay </h1>
 * DepartureTerminalTransferQuay Class implements the SharedRegionInterface and Departure Terminal Transfer Quay shared memory region.
 * In this shared region, the passengers left the bus
 *
 */
package sharedRegions;

import java.util.Random;

import entities.BusDriver;
import entities.BusDriverStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class DepartureTerminalTransferQuay implements SharedRegionInterface {

    private static final Object lock = new Object();

    private RepositoryStub repositoryStub;

    private Integer passengersOnTheBus = 0;
    /**
     * DepartureTerminalTransferQuay constructor.
     * Creates a DepartureTerminalTransferQuay in repository Stub
     * @param repositoryStub that corresponds to Stub Repository
     */
    public DepartureTerminalTransferQuay(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
    }

    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passenger leaveTheBus(int id) method
     * @param id Passenger identifier
     * Passenger leaves the bus when reaches departure terminal
     */
    public  void leaveTheBus(int id){

        try {
            synchronized (lock) {
                lock.wait();
                this.passengersOnTheBus--;
                repositoryStub.removePassengerFromTheBus(id);
            }

            if (this.passengersOnTheBus == 0 ) {
                synchronized (this) {
                    notifyAll(); //Notify the driver
                }
            }

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    /***** BUS DRIVER FUNCTIONS *********/

    /**
     * Driver parkTheBusAndLetPassOff(int passengersOnTheBus) method
     * @param passengersOnTheBus number of the passengers on the bus
     * Driver arrives to departure terminal and notifies the passengers
     * to leave the bus
     */
    public synchronized void parkTheBusAndLetPassOff(int passengersOnTheBus){
        repositoryStub.setBusDriverState(BusDriverStates.PARKING_AT_THE_DEPARTURE_TERMINAL);
        this.passengersOnTheBus = passengersOnTheBus;
        synchronized (lock) {
            lock.notifyAll(); // notify that they can start leaving the bus
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Driver goToArrivalTerminal() method
     * when all passengers leave the bus, the driver goes to arrival drives to arrival terminal
     */
    public void goToArrivalTerminal(){
        repositoryStub.setBusDriverState(BusDriverStates.DRIVING_BACKWARD);
        try {
            Thread.currentThread().sleep((long) (new Random().nextInt(SimulPar.MAX_SLEEP - SimulPar.MIN_SLEEP+1) + SimulPar.MAX_SLEEP));
        } catch (InterruptedException e) {}
    }
}
