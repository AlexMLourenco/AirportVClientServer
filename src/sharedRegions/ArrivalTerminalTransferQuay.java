package sharedRegions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import entities.BusDriver;
import entities.BusDriverStates;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class ArrivalTerminalTransferQuay implements SharedRegionInterface {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryStub repositoryStub;

    /**
     * Queue of passengers waiting for a Bus
     * @serialField waitingForBus
     */
    private Queue<Integer> waitingForBus;

    /**
     * Queue of passengers in the Bus
     * @serialField inTheBus
     */
    private Queue<Integer> inTheBus;

    /**
     * Boolena signal that tells us if the Bus Driver is ready to receive Passengers
     */
    private boolean busDriverReadyToReceivePassengers = false;


    public ArrivalTerminalTransferQuay(RepositoryStub repositoryStub) {
        this.repositoryStub = repositoryStub;
        this.waitingForBus = new LinkedList<>();
        this.inTheBus = new LinkedList<>();
    }

    /***** PASSENGER FUNCTIONS *********/

    public synchronized void takeABus(int id) {
        waitingForBus.add(id);
        repositoryStub.registerPassengerToTakeABus(id);
        if (waitingForBus.size() == SimulPar.BUS_CAPACITY) {
            notifyAll();  //If we have enough persons to wake up the driver let's do it
        }
    }

    /**
     * Passenger is waiting for the Bus to arrive
     *
     @param id int
     *
     */
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

    /**
     * Passenger is put in the list of passengers inside the Bus
     *
     @param id int
     *
     */
    public synchronized void enterTheBus(int id) {
        inTheBus.add(id);
        repositoryStub.registerPassengerToEnterTheBus(id);
        notifyAll();
    }

    /***** DRIVER FUNCTIONS *********/

    /**
     * Bus Driver is ready to Departure
     * Bus Driver reach his schedule
     *
     */
    public synchronized boolean readyToDeparture() {
        this.busDriverReadyToReceivePassengers = false;
        try {
            wait(SimulPar.BUS_SCHEDULE_MILLIS);
        } catch (InterruptedException e) {}

        return (waitingForBus.size() > 0);
    }

    /**
     * Bus Driver drives to the Departure Terminal
     *
     */
    public void goToDepartureTerminal() {
        try {
            repositoryStub.setBusDriverState(BusDriverStates.DRIVING_FORWARD);
            Thread.currentThread().sleep((long) (new Random().nextInt(SimulPar.MAX_SLEEP - SimulPar.MIN_SLEEP + 1) + SimulPar.MAX_SLEEP));
        } catch (InterruptedException e) {
        }
    }

    /**
     * Bus Driver parks the Bus
     *
     */
    public void parkTheBus() {
        repositoryStub.setBusDriverState(BusDriverStates.PARKING_AT_THE_ARRIVAL_TERMINAL);
    }

    /**
     * Bus Driver announce Bus Boarding of the Passengers
     *
     */
    public synchronized void announcingBusBoarding() {
        BusDriver busDriver = (BusDriver) Thread.currentThread();
        this.busDriverReadyToReceivePassengers = true;
        this.inTheBus.clear();
        int numberOfPassengers = (waitingForBus.size() > 3 ? 3 : waitingForBus.size());
        busDriver.setPassengersInTheBus(numberOfPassengers);
        notifyAll();
        try {
            while  (numberOfPassengers != inTheBus.size()) {
                wait(2000);
            }
        } catch (InterruptedException e) {}

        this.busDriverReadyToReceivePassengers = false;

        for (int i = 0; i < numberOfPassengers; i++) {
            waitingForBus.poll();
        }
    }

    /**
     * Set if Bus Driver's work day is over
     *
     */
    public synchronized void setBusDriverEndOfWork() {
        notifyAll();
    }

}
