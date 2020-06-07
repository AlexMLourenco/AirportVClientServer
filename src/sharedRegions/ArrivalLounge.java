/**
 * <h1>Arrival Lounge</h1>
 * ArrivalLounge Class implements SharedRegionInterface and the Arrival Lounge shared memory region.
 * In this shared region, is where the porter collects the bags
 * and passengers arrive decide what to do next
 *
 */

package sharedRegions;

import java.util.Stack;

import commonInfra.BAG;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class ArrivalLounge implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    Stack<BAG> planeHold;

    private int passengersCount;

    /**
     * ArrivalLounge constructor.
     * Creates a ArrivalLounge in specified repository Stub
     * @param repositoryStub that corresponds to Stub Repository
     */
    public ArrivalLounge(RepositoryStub repositoryStub) {
        this.repositoryStub = repositoryStub;
        this.planeHold = new Stack<>();
    }

    /**
     * Porter takeARest() method
     * */
    public synchronized void takeARest() {
        try {
            wait();
        } catch (InterruptedException e) {}
    }

    /**
     *  Porter noMoreBagsToCollect() method
     * @return {@code true} means the the planeHold is empty and there's no more bags to collect
     *                    otherwise {@code false}
     * */
    public synchronized boolean noMoreBagsToCollect() {
        return planeHold.empty();
    }

    /**
     * Porter tryToCollectABag() method
     * @return bag that is in the plain hold
     * */
    public synchronized BAG tryToCollectABag() {
        repositoryStub.removeLuggageInPlainHold();
        BAG b = planeHold.pop();
        return b;
    }


    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passenger whatShouldIDo() method
     * @param id the passenger id
     * @param isFinalDestination boolean to check if its passenger final destination
     * @param numberOfLuggages number of Luggages of the passenger
     * Depending on the destination, number of luggages, passenger can take 3 different actions:
     *          Take a Bus -> if passenger isn't in his final destination yet and needs to go to departure terminal
     *          Collect a Bag -> if passenger reaches his final destination and needs to pick up his luggage
     *                           on baggage collection point
     *                              if his luggage is not found, he needs to go to reclaim office
     *                              and then go home
     *          Go Home -> passenger leaves the airport
     * @returns action 'B' || 'C' || 'H'
     *
     * **/
    public synchronized char whatShouldIDo(int id, boolean isFinalDestination, int numberOfLuggages) {
        this.passengersCount ++;
        char action = repositoryStub.passengerArrived(id, isFinalDestination, numberOfLuggages);
        if (this.passengersCount == SimulPar.PASSENGERS) {
            notifyAll(); // The last passenger to arrive will wake up the porter.
        }
        return action;
    }

    /***** MAIN THREAD *********/
    /**
     * Initialize plane hold for each passenger
     * @param flightNumber flight number
     * @param plainHoldLuggage Plain Hold Luggage
     * @param passengersFinalDestination defines if its passengers final destination or not
     * **/
    public synchronized void init_plane_hold(int flightNumber, int [][] plainHoldLuggage, boolean [][] passengersFinalDestination ) {
        this.passengersCount = 0;
        planeHold.clear();
        for (int i = 0; i < SimulPar.PASSENGERS; i ++) {
            for (int j = 0; j < plainHoldLuggage[flightNumber][i]; j++) {
                BAG bag = new BAG(i, passengersFinalDestination[flightNumber][i]);
                planeHold.push(bag);
            }
        }
        repositoryStub.flightLanded(planeHold.size());
    }

    /**
     * Porter End of Work
     * **/
    public synchronized void setPorterEndOfWork() {
        notifyAll();
    }
}
