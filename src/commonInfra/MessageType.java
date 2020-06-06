/**
 * <h1>Message Type</h1>
 * The Message class implements the methods to
 * process messages between server and client
 */


/**
 * commonInfra is a group of bar utils for operating on foo things.
 */

package commonInfra;

public enum MessageType {

    REPLY_OK,

    ARRIVAL_LOUNGE_TAKE_A_REST,
    ARRIVAL_LOUNGE_NO_MORE_BAGS_TO_COLLECT,
    ARRIVAL_LOUNGE_TRY_TO_COLLECT_A_BAG,
    ARRIVAL_LOUNGE_WHAT_SHOULD_I_DO,
    ARRIVAL_LOUNGE_INIT_PLANE_HOLD,
    ARRIVAL_LOUNGE_SET_PORTER_END_OF_WORK,
    ARRIVAL_LOUNGE_SIMULATION_FINISHED,

    BAGGAGE_COLLECTION_POINT_WARNING_NO_MORE_BAGS_IN_PLANE_HOLD,
    BAGGAGE_COLLECTION_POINT_CARRY_IT_TO_APPROPRIATE_STORE,
    BAGGAGE_COLLECTION_POINT_GO_COLLECT_BAG,
    BAGGAGE_COLLECTION_POINT_CLEAN_UP,
    BAGGAGE_COLLECTION_POINT_SIMULATION_FINISHED,

    BAGGAGE_RECLAIM_OFFICE_RECLAIM_MISSING_BAG,
    BAGGAGE_RECLAIM_OFFICE_SIMULATION_FINISHED,

    TEMPORARY_STORAGE_AREA_CARRY_IT_TO_APPROPRIATE_STORE,
    TEMPORARY_STORAGE_AREA_SIMULATION_FINISHED,

    ARRIVAL_TERMINAL_TRANSFER_QUAY_READY_TO_DEPARTUE,
    ARRIVAL_TERMINAL_TRANSFER_QUAY_ANNOUNCING_BUS_BOARDING,
    ARRIVAL_TERMINAL_TRANSFER_QUAY_GO_TO_DEPARTURE_TERMINAL,
    ARRIVAL_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS,
    ARRIVAL_TERMINAL_TRANSFER_QUAY_TAKE_A_BUS,
    ARRIVAL_TERMINAL_TRANSFER_QUAY_WAIT_FOR_BUS,
    ARRIVAL_TERMINAL_TRANSFER_QUAY_ENTER_THE_BUS,
    ARRIVAL_TERMINAL_TRANSFER_QUAY_SET_BUS_DRIVER_END_OF_WORK,
    ARRIVAL_TERMINAL_TRANSFER_QUAY_SIMULATION_FINISHED,

    DEPARTURE_TERMINAL_TRANSFER_QUAY_LEAVE_THE_BUS,
    DEPARTURE_TERMINAL_TRANSFER_QUAY_PARK_THE_BUS_AND_LET_PASS_OFF,
    DEPARTURE_TERMINAL_TRANSFER_QUAY_GO_TO_ARRIVAL_TERMINAL,
    DEPARTURE_TERMINAL_TRANSFER_QUAY_SIMULATION_FINISHED,


    DEPARTURE_TERMINAL_ENTRANCE_PREPARE_NEXT_LEG,
    DEPARTURE_TERMINAL_ENTRANCE_GET_NUMBER_OF_PASSENGERS,
    DEPARTURE_TERMINAL_ENTRANCE_READY_TO_LEAVE,
    DEPARTURE_TERMINAL_ENTRANCE_CLEAN_UP,
    DEPARTURE_TERMINAL_ENTRANCE_SIMULATION_FINISHED,


    ARRIVAL_TERMINAL_EXIT_GO_HOME,
    ARRIVAL_TERMINAL_EXIT_CLEAN_UP,
    ARRIVAL_TERMINAL_EXIT_GET_NUMBER_OF_PASSENGERS,
    ARRIVAL_TERMINAL_EXIT_READY_TO_LEAVE,
    ARRIVAL_TERMINAL_SIMULATION_FINISHED,

    REPOSITORY_INIT,
    REPOSITORY_PASSENGER_ARRIVED,
    REPOSITORY_REMOVE_LUGGAGE_IN_PLANE_HOLD,
    REPOSITORY_REMOVE_PASSENGER_FROM_BUS,
    REPOSITORY_REGISTER_PASSENGER_TO_ENTER_THE_BUS,
    REPOSITORY_REGISTER_PASSENGER_TO_TAKE_A_BUS,
    REPOSITORY_REGISTER_COLLECTED_LUGGAGE,
    REPOSITORY_REGISTER_LUGGAGE_IN_STORE_ROOM,
    REPOSITORY_REGISTER_LUGGAGE_IN_CONVEYOR_BELT,
    REPOSITORY_FLIGHT_LANDED,
    REPOSITORY_SET_PORTER_STATE,
    REPOSITORY_SET_BUS_DRIVER_STATE,
    REPOSITORY_SET_PASSENGER_STATE,
    REPOSITORY_FINAL_STATS,
    REPOSITORY_SIMULATION_FINISHED

}
