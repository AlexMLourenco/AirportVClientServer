/**
 * <h1>PassengerStates</h1>
 * The PassengerStates enum class implements StateInterface methods to get the value of the State,
 * and enumerates the states that Passenger can take all long the simulation
 * The possible states are:
 *
 *  <li>{@link #WHAT_SHOULD_I_DO}</li>
 *  <li>{@link #AT_THE_ARRIVAL_TRANSFER_TERMINAL("ATT")}</li>
 *  <li>{@link #TERMINAL_TRANSFER("TRT")}</li>
 *  <li>{@link #AT_THE_DEPARTURE_TRANSFER_TERMINAL("DTT")}</li>
 *  <li>{@link #ENTERING_THE_DEPARTURE_TERMINAL("EDT")}</li>
 *  <li>{@link #AT_THE_LUGGAGE_COLLECTION_POINT("LCP")}</li>
 *  <li>{@link #AT_THE_BAGGAGE_RECLAIM_OFFICE("BRO")}</li>
 *  <li>{@link #EXITING_THE_ARRIVAL_TERMINAL("EAT")}</li>
 */

package entities;

import java.io.Serializable;

public enum PassengerStates implements StateInterface, Serializable {
    WHAT_SHOULD_I_DO("WSD"),
    AT_THE_ARRIVAL_TRANSFER_TERMINAL("ATT"),
    TERMINAL_TRANSFER("TRT"),
    AT_THE_DEPARTURE_TRANSFER_TERMINAL("DTT"),
    ENTERING_THE_DEPARTURE_TERMINAL("EDT"),
    AT_THE_LUGGAGE_COLLECTION_POINT("LCP"),
    AT_THE_BAGGAGE_RECLAIM_OFFICE("BRO"),
    EXITING_THE_ARRIVAL_TERMINAL("EAT");

    private String value;
    /**
     * PassengerStates constructor.
     * Creates a PassengerState in a specific state depending on value
     * @param value that corresponds to the specific state
     */
    PassengerStates(String value) {
        this.value = value;
    }

    /**
     * Returns the value of the sate.
     * @return value of the current state of the Passenger
     */
    public String getValue() {
        return this.value;
    }
}
