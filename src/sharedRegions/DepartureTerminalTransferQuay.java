package sharedRegions;

import java.util.Random;

import entities.BusDriver;
import entities.BusDriverStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class DepartureTerminalTransferQuay implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    private int passengersOnTheBus;

    public DepartureTerminalTransferQuay(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
    }

    /***** PASSENGER FUNCTIONS *********/

    public synchronized void leaveTheBus(int id){
        try {
            wait();
            this.passengersOnTheBus--;
            if (this.passengersOnTheBus == 0 ) {
                notifyAll(); //Notify the driver
            }
            repositoryStub.removePassengerFromTheBus(id);
        }catch(InterruptedException e){}
    }

    /***** BUS DRIVER FUNCTIONS *********/

    /**
     * Bus Driver parks the Bus and let the Passengers off
     *
     */
    public synchronized void parkTheBusAndLetPassOff(){
        repositoryStub.setBusDriverState(BusDriverStates.PARKING_AT_THE_DEPARTURE_TERMINAL);
        BusDriver busDriver = (BusDriver) Thread.currentThread();
        passengersOnTheBus = busDriver.getPassengersInTheBus();
        notifyAll(); //Notify the passengers that they can start leaving the bus
        try {
            wait(); //wait for the last passenger to leave the bus
        } catch (Exception e ) {

        }
    }

    /**
     * Bus Driver drives to the Arrival Terminal
     *
     */
    public synchronized void goToArrivalTerminal(){
        repositoryStub.setBusDriverState(BusDriverStates.DRIVING_BACKWARD);
        try {
            Thread.currentThread().sleep((long) (new Random().nextInt(SimulPar.MAX_SLEEP - SimulPar.MIN_SLEEP+1) + SimulPar.MAX_SLEEP));
        } catch (InterruptedException e) {}
    }
}
