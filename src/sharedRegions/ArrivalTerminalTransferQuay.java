package sharedRegions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import entities.BusDriverStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class ArrivalTerminalTransferQuay implements SharedRegionInterface {

    private static final Object lock = new Object();

    private RepositoryStub repositoryStub;

    private Queue<Integer> waitingForBus;

    private Queue<Integer> inTheBus;

    private boolean busDriverReadyToReceivePassengers = false;

    public ArrivalTerminalTransferQuay(RepositoryStub repositoryStub) {
        this.repositoryStub = repositoryStub;
        this.waitingForBus = new LinkedList<>();
        this.inTheBus = new LinkedList<>();
    }

    /***** PASSENGER FUNCTIONS *********/

    /** DONE **/
    public synchronized void takeABus(int id) {
        if (SimulPar.DEBUG_MODE) System.out.println("takeABus ( " + id + "): start ");
        waitingForBus.add(id);
        repositoryStub.registerPassengerToTakeABus(id);
        if (waitingForBus.size() == SimulPar.BUS_CAPACITY) {
            if (SimulPar.DEBUG_MODE) System.out.println("takeABus ( " + id + "): enough persons ");
            notifyAll();  //If we have enough persons to wake up the driver let's do it
        }
    }

    /** DONE **/
    public void waitForBus(int id) {
        try {
            while (true) {
                synchronized (lock) {
                    if (SimulPar.DEBUG_MODE) System.out.println("waitForBus ( " + id + "): wait ");
                    lock.wait();
                    if (SimulPar.DEBUG_MODE) System.out.println("waitForBus ( " + id + "): unblocked ");
                    if (this.busDriverReadyToReceivePassengers) {
                        if (SimulPar.DEBUG_MODE) System.out.println("waitForBus ( " + id + "): on the bus " +  inTheBus.size());
                        if (id == waitingForBus.element() && inTheBus.size() < SimulPar.BUS_CAPACITY) {
                            if (SimulPar.DEBUG_MODE) System.out.println("waitForBus ( " + id + "): out " + waitingForBus.element());
                            return;
                        }
                    }
                }
            }
        } catch (InterruptedException e) { }
    }

    /** DONE **/
    public synchronized void enterTheBus(int id) {
        if (SimulPar.DEBUG_MODE) System.out.println("enterTheBus ( " + id + ") ");
        synchronized (lock) {
            waitingForBus.poll();
            inTheBus.add(id);
            repositoryStub.registerPassengerToEnterTheBus(id);
            lock.notifyAll();
            if (SimulPar.DEBUG_MODE) System.out.println("enterTheBus ( " + id + ") notify others" );
        }
        notify();
    }

    /***** DRIVER FUNCTIONS *********/

    /** DONE **/
    public synchronized boolean readyToDeparture() {
        if (SimulPar.DEBUG_MODE) System.out.println("readyToDeparture : ");
        this.busDriverReadyToReceivePassengers = false;
        try {
            wait(SimulPar.BUS_DRIVER_SCHEDULE_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (SimulPar.DEBUG_MODE) System.out.println("readyToDeparture :  "  + (waitingForBus.size() > 0) + " + " + waitingForBus.size());
        return (waitingForBus.size() > 0);
    }

    /** DONE **/
    public void goToDepartureTerminal() {
        try {
            repositoryStub.setBusDriverState(BusDriverStates.DRIVING_FORWARD);
            Thread.currentThread().sleep((long) (new Random().nextInt(SimulPar.MAX_SLEEP - SimulPar.MIN_SLEEP + 1) + SimulPar.MAX_SLEEP));
        } catch (InterruptedException e) {
        }
    }

    /** DONE **/
    public void parkTheBus() {
        repositoryStub.setBusDriverState(BusDriverStates.PARKING_AT_THE_ARRIVAL_TERMINAL);
    }

    /** DONE **/
    public synchronized int announcingBusBoarding() {
        int numberOfPassengers;
        this.busDriverReadyToReceivePassengers = true;
        this.inTheBus.clear();
        numberOfPassengers = (waitingForBus.size() > 3 ? 3 : waitingForBus.size());
        synchronized (lock) {
            lock.notifyAll();
        }
        try {
            while  (numberOfPassengers != inTheBus.size()) {
                wait();
                if (SimulPar.DEBUG_MODE) System.out.println("announcingBusBoarding: inTheBus " + inTheBus.size() + " passengers " + numberOfPassengers);
            }
        } catch (InterruptedException e) {}
        if (SimulPar.DEBUG_MODE) System.out.println("announcingBusBoarding: continue");
        this.busDriverReadyToReceivePassengers = false;
        return numberOfPassengers;
    }

    /** DONE **/
    public synchronized void setBusDriverEndOfWork() {
        notifyAll();
    }

}
