/**
 * <h1>BusDriver</h1>
 * The BusDriver class implements methods to create a Bus Driver, his lifecycle
 * and all the possible actions he's able to do
 */

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

    /**
     * BusDriver constructor.
     * Creates a BusDriver in the specific shared regions
     * @param arrivalTerminalTransferQuayStub Arrival Terminal TransferQuay shared region
     * @param departureTerminalTransferQuayStub Departure Terminal Transfer Quay shared region
     */
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
     * Extended version of {@link #run()}.
     * Bus Driver's lifecycle
     *      while Bus Driver doesn't reach the end of the day he will drive the passengers
     *      through arrival and departure terminal
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
    /**
     * Returns the current number of passengers in the bus.
     * @return passengersInTheBus the current number of passengers in the bus
     */
    public int getPassengersInTheBus() {
        return passengersInTheBus;
    }

    /** Updates the number of passengers in the bus.
     * @param passengersInTheBus The set value of the number of passengers.
     */
    public void setPassengersInTheBus(int passengersInTheBus) {
        this.passengersInTheBus = passengersInTheBus;
    }

    /** Updates the boolean keepAlive.
     * @param keepAlive {@code true} means the day isn't over yet
     *             otherwise {@code false}
     */
    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}

