package entities;

import mainProject.SimulPar;
import stubs.ArrivalTerminalTransferQuayStub;
import stubs.DepartureTerminalTransferQuayStub;

public class BusDriver extends Thread {

    private long activityStarted;
    private int passengersInTheBus;
    private boolean keepAlive;

    private ArrivalTerminalTransferQuayStub arrivalTerminalTransferQuayStub;
    private DepartureTerminalTransferQuayStub departureTerminalTransferQuayStub;

    public BusDriver(ArrivalTerminalTransferQuayStub arrivalTerminalTransferQuayStub,
                     DepartureTerminalTransferQuayStub departureTerminalTransferQuayStub){
        super("Bus Driver");
        this.arrivalTerminalTransferQuayStub = arrivalTerminalTransferQuayStub;
        this.departureTerminalTransferQuayStub = departureTerminalTransferQuayStub;
        this.keepAlive = true;
    }

    /**
     * Check if the day of work of the Bus Driver has come to an end.
     * If it does the thread goes to sleep and wakes up a sleep_time later.
     * */
    private void checkWorkDayEnded() {
        if ((System.nanoTime() / 1000) - activityStarted > SimulPar.BUS_DRIVER_END_OF_DAY_DURATION_MILLIS) {
            try {
                sleep(SimulPar.BUS_DRIVER_END_OF_DAY_SLEEP_MILLIS);
            } catch (InterruptedException e) { e.printStackTrace(); }
            activityStarted = System.nanoTime() / 1000;
        }
    }

    /**
     * Bus Driver's lifecycle
     */
    @Override
    public void run() {
        activityStarted = System.nanoTime() / 1000;
        while (this.keepAlive) {
            this.checkWorkDayEnded();
            if (arrivalTerminalTransferQuayStub.readyToDeparture()) {
                this.setPassengersInTheBus(arrivalTerminalTransferQuayStub.announcingBusBoarding());
                arrivalTerminalTransferQuayStub.goToDepartureTerminal();
                departureTerminalTransferQuayStub.parkTheBusAndLetPassOff(this.passengersInTheBus);
                departureTerminalTransferQuayStub.goToArrivalTerminal();
                arrivalTerminalTransferQuayStub.parkTheBus();
            }
        }
    }

    public int getPassengersInTheBus() {
        return passengersInTheBus;
    }

    public void setPassengersInTheBus(int passengersInTheBus) {
        this.passengersInTheBus = passengersInTheBus;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}

