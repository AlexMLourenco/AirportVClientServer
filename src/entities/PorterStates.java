/**
 * <h1>PorterStates</h1>
 * The PorterStates enum class implements StateInterface methods to get the value of the State,
 * and enumerates the states that Porter can take all long the simulation
 * The possible states are:
 *
 *  <li>{@link #WAITING_FOR_A_PLANE_TO_LAND ("WPTL")}</li>
 *  <li>{@link #AT_THE_PLANES_HOLD("APLH")}</li>
 *  <li>{@link #AT_THE_LUGGAGE_BELT_CONVEYOR("ALCB")}</li>
 *  <li>{@link #AT_THE_STOREROOM("ASTR")}</li>

 */

package entities;

import java.io.Serializable;

public enum PorterStates implements StateInterface, Serializable {
    WAITING_FOR_A_PLANE_TO_LAND ("WPTL"),
    AT_THE_PLANES_HOLD("APLH"),
    AT_THE_LUGGAGE_BELT_CONVEYOR("ALCB"),
    AT_THE_STOREROOM("ASTR");

    private String value;

    /**
     * PassengerStates constructor.
     * Creates a PassengerState in a specific state depending on value
     * @param value that corresponds to the specific state
     */
    PorterStates(String value) {
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
