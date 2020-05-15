package mainProject;

import java.util.Date;

public class SimulPar {

    /**
     * Number of Passengers
     */
    public static final int PASSENGERS = 6;

    /**
     * Number of Plane Landings
     */
    public static final int LANDINGS = 5;

    /**
     * Maximum number of pieces of luggage by each passenger
     */
    public static final int LUGGAGE = 2;

    /**
     * Maximum Capacity of seating places in the bus
     */
    public static final int BUS_CAPACITY = 3;

    /**
     * Bus Driver Departure Schedule (every x millis)
     */
    public static final int BUS_SCHEDULE_MILLIS = 1000;

    /**
     * Bus Driver End Of Day at (every x millis)
     */
    public static final int BUS_END_OF_DAY_MILLIS = 5000;

    /**
     * Bus Driver sleep until restart new day (millis)
     */
    public static final int BUS_SLEEP_MILLIS= 5000;

    /**
     * Minimum milliseconds to wakeup.
     */
    public static final int MIN_SLEEP = 5;

    /**
     * Maximum milliseconds to wakeup.
     */
    public static final int MAX_SLEEP = 10;


    public static final int BUS_DRIVER_SLEEP = 250;

    /**
     * Repository log filename
     */
    public static final String FILENAME = "log_" + new Date().toString().replace(' ', '_') + ".txt";




    /******************** CLIENT SERVER COMMUNICATIONS *************************/

    public static final String SERVER_REPOSITORY_HOSTNAME = "localhost";
    public static final int SERVER_REPOSITORY_PORT = 21000;

    public static final String SERVER_ARRIVAL_LOUNGE_HOSTNAME = "localhost";
    public static final int SERVER_ARRIVAL_LOUNGE_PORT = 21001;

    public static final String SERVER_ARRIVAL_TERMINAL_EXIT_HOSTNAME = "localhost";
    public static final int SERVER_ARRIVAL_TERMINAL_EXIT_PORT = 21002;

    public static final String SERVER_ARRIVAL_TERMINAL_TRANSFER_QUAY_HOSTNAME = "localhost";
    public static final int SERVER_ARRIVAL_TERMINAL_TRANSFER_QUAY_PORT = 21003;

    public static final String SERVER_BAGGAGE_COLLECTION_POINT_HOSTNAME = "localhost";
    public static final int SERVER_BAGGAGE_COLLECTION_POINT_PORT = 21004;

    public static final String SERVER_BAGGAGE_RECLAIM_OFFICE_HOSTNAME = "localhost";
    public static final int SERVER_BAGGAGE_RECLAIM_OFFICE_PORT = 21005;

    public static final String SERVER_DEPARTURE_TERMINAL_ENTRANCE_HOSTNAME = "localhost";
    public static final int SERVER_DEPARTURE_TERMINAL_ENTRANCE_PORT = 21006;

    public static final String SERVER_DEPARTURE_TERMINAL_TRANSFER_QUAY_HOSTNAME = "localhost";
    public static final int SERVER_DEPARTURE_TERMINAL_TRANSFER_QUAY_PORT = 21007;

    public static final String SERVER_TEMPORRY_STORAGE_AREA_HOSTNAME = "localhost";
    public static final int SERVER_TEMPORRY_STORAGE_AREA_PORT = 21008;

    public static String getHostNameForSharedRegion(String region) {
        if (region.equalsIgnoreCase("ArrivalTerminalExit")){
            return SERVER_ARRIVAL_TERMINAL_EXIT_HOSTNAME;
        } else if (region.equalsIgnoreCase("ArrivalLounge")) {
            return SERVER_ARRIVAL_LOUNGE_HOSTNAME;
        } else if (region.equalsIgnoreCase("ArrivalTerminalTransferQuay")) {
            return SERVER_ARRIVAL_TERMINAL_TRANSFER_QUAY_HOSTNAME;
        } else if (region.equalsIgnoreCase("BaggageCollectionPoint")){
            return SERVER_BAGGAGE_COLLECTION_POINT_HOSTNAME;
        } else if (region.equalsIgnoreCase("BaggageReclaimOffice")) {
            return SERVER_BAGGAGE_RECLAIM_OFFICE_HOSTNAME;
        } else if (region.equalsIgnoreCase("DepartureTerminalEntrance")) {
            return SERVER_DEPARTURE_TERMINAL_ENTRANCE_HOSTNAME;
        } else if (region.equalsIgnoreCase("DepartureTerminalTransferQuay")) {
            return SERVER_DEPARTURE_TERMINAL_TRANSFER_QUAY_HOSTNAME;
        }else if (region.equalsIgnoreCase("TemporaryStorageArea")) {
            return SERVER_TEMPORRY_STORAGE_AREA_HOSTNAME;
        }

        return null;
    }

    public static int getPortForSharedRegion(String region) {
        if (region.equalsIgnoreCase("ArrivalTerminalExit")){
            return SERVER_ARRIVAL_TERMINAL_EXIT_PORT;
        } else if (region.equalsIgnoreCase("ArrivalLounge")) {
            return SERVER_ARRIVAL_LOUNGE_PORT;
        } else if (region.equalsIgnoreCase("ArrivalTerminalTransferQuay")) {
            return SERVER_ARRIVAL_TERMINAL_TRANSFER_QUAY_PORT;
        } else if (region.equalsIgnoreCase("BaggageCollectionPoint")){
            return SERVER_BAGGAGE_COLLECTION_POINT_PORT;
        } else if (region.equalsIgnoreCase("BaggageReclaimOffice")) {
            return SERVER_BAGGAGE_RECLAIM_OFFICE_PORT;
        }else if (region.equalsIgnoreCase("DepartureTerminalEntrance")) {
            return SERVER_DEPARTURE_TERMINAL_ENTRANCE_PORT;
        } else if (region.equalsIgnoreCase("DepartureTerminalTransferQuay")) {
            return SERVER_DEPARTURE_TERMINAL_TRANSFER_QUAY_PORT;
        } else if (region.equalsIgnoreCase("TemporaryStorageArea")) {
            return SERVER_TEMPORRY_STORAGE_AREA_PORT;
        }
        return -1;
    }
}
