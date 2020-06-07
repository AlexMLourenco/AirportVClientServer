/**
 * <h1>BusDriverStates</h1>
 * The BusDriverStates enum class implements StateInterface methods to get the value of the State,
 * and enumerates the states that Bus Driver can take all long the simulation
 * The possible states are:
 *
 *  <li>{@link  #PARKING_AT_THE_ARRIVAL_TERMINAL("PKAT")}</li>
 *  <li>{@link #DRIVING_FORWARD("DRFW")}</li>
 *  <li>{@link #PARKING_AT_THE_DEPARTURE_TERMINAL("PKDT")}</li>
 *  <li>{@link #PARKING_AT_THE_DEPARTURE_TERMINAL("PKDT")}</li>
 *  <li>{@link #DRIVING_BACKWARD("DRBW")}</li>
 */

package entities;

import java.io.Serializable;

public enum BusDriverStates implements StateInterface, Serializable {
    PARKING_AT_THE_ARRIVAL_TERMINAL("PKAT"),
    DRIVING_FORWARD("DRFW"),
    PARKING_AT_THE_DEPARTURE_TERMINAL("PKDT"),
    DRIVING_BACKWARD("DRBW");

    private String value;

    /**
     * BusDriverStates constructor.
     * Creates a BusDriverState in a specific state depending on value
     * @param value that corresponds to the specific state
     */
    BusDriverStates(String value) {
        this.value = value;
    }

    /**
     * Returns the value of the sate.
     * @param arguments unused
     * @return value of the current state of the bus driver
     */
    public String getValue() {
        return this.value;
    }

}
