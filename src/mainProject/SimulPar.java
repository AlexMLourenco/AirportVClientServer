package mainProject;

import java.util.Date;

public class SimulPar {

    public static final boolean DEBUG_MODE = false;

    /**
     * Number of Passengers
     */
    public static final int PASSENGERS = 6;

    /**
     * Number of Plane Landings
     */
    public static final int LANDINGS = 5 ;

    /**
     * Maximum number of pieces of luggage by each passenger
     */
    public static final int LUGGAGE = 2;

    /**
     * Maximum Capacity of seating places in the bus
     */
    public static final int BUS_CAPACITY = 3;

    public static final int BUS_DRIVER_SCHEDULE_MILLIS = 1000;

    public static final int BUS_DRIVER_END_OF_DAY_DURATION_MILLIS = 5000;

    public static final int BUS_DRIVER_END_OF_DAY_SLEEP_MILLIS = 100;


    /**
     * Minimum milliseconds to wakeup.
     */
    public static final int MIN_SLEEP = 5;

    /**
     * Maximum milliseconds to wakeup.
     */
    public static final int MAX_SLEEP = 10;




    public static final int REPORT_MISSING_BAG_SLEEP = 100; //Millis

    /**
     * Repository log filename
     */
    public static final String FILENAME = "log_" + new Date().toString().replace(' ', '_') + ".txt";



}
