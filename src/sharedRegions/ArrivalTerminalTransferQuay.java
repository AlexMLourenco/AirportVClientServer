package sharedRegions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import entities.BusDriverStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class ArrivalTerminalTransferQuay implements SharedRegionInterface {

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
        System.out.println(id);
        waitingForBus.add(id);
        repositoryStub.registerPassengerToTakeABus(id);
        if (waitingForBus.size() == SimulPar.BUS_CAPACITY) {
            notifyAll();  //If we have enough persons to wake up the driver let's do it
        }
    }

    /** DONE **/
    public synchronized void waitForBus(int id) {
        while (true) {
            try {
                wait();
                if (this.busDriverReadyToReceivePassengers) {
                    Object[] tempArr = waitingForBus.toArray();

                    for (int i = 0; i < 3; i++) {
                        if (id == (Integer) tempArr[i]) {
                            return;
                        }
                    }
                }
            } catch (InterruptedException e) { }
        }
    }

    /** DONE **/
    public synchronized void enterTheBus(int id) {
        inTheBus.add(id);
        repositoryStub.registerPassengerToEnterTheBus(id);
        notifyAll();
    }

    /***** DRIVER FUNCTIONS *********/

    /** DONE **/
    public synchronized boolean readyToDeparture() {
        this.busDriverReadyToReceivePassengers = false;
        try {
            wait(SimulPar.BUS_DRIVER_SCHEDULE_MILLIS);
        } catch (InterruptedException e) {}

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
        this.busDriverReadyToReceivePassengers = true;
        this.inTheBus.clear();
        int numberOfPassengers = (waitingForBus.size() > 3 ? 3 : waitingForBus.size());
        notifyAll();
        try {
            while  (numberOfPassengers != inTheBus.size()) {
                wait();
                System.out.println("announcingBusBoarding: inTheBus " + inTheBus.size() + " passengers " + numberOfPassengers);
            }
        } catch (InterruptedException e) {}

        this.busDriverReadyToReceivePassengers = false;

        for (int i = 0; i < numberOfPassengers; i++) {
            waitingForBus.poll();
        }
        return numberOfPassengers;
    }

    /** DONE **/
    public synchronized void setBusDriverEndOfWork() {
        notifyAll();
    }

}
