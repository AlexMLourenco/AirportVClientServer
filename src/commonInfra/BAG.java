/**
 * <h1>Bag</h1>
 * The BAG class implements methods to create a Bag, get the passenger to whom the bag belongs and
 * get the type of the destination
 */


package commonInfra;

import java.io.Serializable;

public class BAG implements Serializable {

    private int passenger;
    private boolean isFinalDestination;

    /**
     *   BAG instantiation.
     *     Creates a Bag associated to the passenger with the specified type of destination
     *     @param passenger passenger id
     *     @param isFinalDestination isFinalDestination indicates if it's the final destination of the passenger
     */
    public BAG(int passenger, boolean isFinalDestination) {
        this.passenger = passenger;
        this.isFinalDestination = isFinalDestination;
    }

    /**
     *   BAG passenger retrieval.
     *
     *    @return passenger to whom the bag belongs
     */
    public int getPassenger() {
        return passenger;
    }

    /**
     * BAG destination retrieval
     * @return {@code true}  if passenger arrives the final destination
     *         otherwise {@code false}
     *
     */
    public boolean isFinalDestination() {
        return isFinalDestination;
    }
}
