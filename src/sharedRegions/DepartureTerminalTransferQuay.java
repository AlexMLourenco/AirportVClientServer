package sharedRegions;

import java.util.Random;

import entities.BusDriver;
import entities.BusDriverStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class DepartureTerminalTransferQuay implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    private Integer passengersOnTheBus = 0;

    public DepartureTerminalTransferQuay(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
    }

    /***** PASSENGER FUNCTIONS *********/

    /** DONE **/
    public synchronized  void leaveTheBus(int id){

        try {
            System.out.println("Passenger " + id + " : is on a ride!");
            wait();
            System.out.println("Passenger " + id + " : arrived ");
            this.passengersOnTheBus--;
            System.out.println("Passenger " + id + " : " + passengersOnTheBus);
            repositoryStub.removePassengerFromTheBus(id);
            if (this.passengersOnTheBus == 0 ) {
                notifyAll(); //Notify the driver
            }

        }catch(InterruptedException e){}
    }

    /***** BUS DRIVER FUNCTIONS *********/

    /** DONE **/
    public synchronized void parkTheBusAndLetPassOff(int passengersOnTheBus){
        repositoryStub.setBusDriverState(BusDriverStates.PARKING_AT_THE_DEPARTURE_TERMINAL);
        this.passengersOnTheBus = passengersOnTheBus;
        notifyAll(); // notify that they can start leaving the bus
        try {
            while (this.passengersOnTheBus != 0) {
                wait();
            }
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
