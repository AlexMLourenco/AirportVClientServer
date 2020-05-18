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
        while (true) {
            System.out.println("Bus Driver: Start");
            this.checkWorkDayEnded();
            System.out.println("Bus Driver: checkWorkDayEnded");
            if (arrivalTerminalTransferQuayStub.readyToDeparture()) {
                System.out.println("Bus Driver: Ready to Departure");
                this.setPassengersInTheBus(arrivalTerminalTransferQuayStub.announcingBusBoarding());
                System.out.println("Bus Driver: Passengers in the Bus " + this.getPassengersInTheBus());
                arrivalTerminalTransferQuayStub.goToDepartureTerminal();
                System.out.println("Bus Driver: goToDepartureTerminal");
                departureTerminalTransferQuayStub.parkTheBusAndLetPassOff(this.passengersInTheBus);
                System.out.println("Bus Driver: parkTheBusAndLetPassOff");
                departureTerminalTransferQuayStub.goToArrivalTerminal();
                System.out.println("Bus Driver: goToArrivalTerminal");
                arrivalTerminalTransferQuayStub.parkTheBus();
                System.out.println("Bus Driver: parkTheBus");
            }
            if (!this.keepAlive) break;
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

