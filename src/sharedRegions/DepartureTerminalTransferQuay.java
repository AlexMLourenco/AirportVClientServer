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

    public DepartureTerminalTransferQuay(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
    }

    /***** PASSENGER FUNCTIONS *********/

    /** DONE **/
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

    /** DONE **/
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

    /** DONE **/
    public void goToArrivalTerminal(){
        repositoryStub.setBusDriverState(BusDriverStates.DRIVING_BACKWARD);
        try {
            Thread.currentThread().sleep((long) (new Random().nextInt(SimulPar.MAX_SLEEP - SimulPar.MIN_SLEEP+1) + SimulPar.MAX_SLEEP));
        } catch (InterruptedException e) {}
    }
}
