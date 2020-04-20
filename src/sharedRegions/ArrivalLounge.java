package sharedRegions;

import java.util.Stack;

import commonInfra.BAG;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class ArrivalLounge implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    /*** simulation purposes only ***/
    private int[][] plainHoldLuggage;
    private boolean[][] passengersFinalDestination;

    Stack<BAG> planeHold;

    private int passengersCount;


    public ArrivalLounge(RepositoryStub repositoryStub) {
        this.repositoryStub = repositoryStub;
        this.planeHold = new Stack<>();
        //this.plainHoldLuggage = plainHoldLuggage;
        //this.passengersFinalDestination = passengersFinalDestination;
    }





    /*****  PORTER FUNCTIONS *****/

    /**
     * Porter takes a Rest
     */
    public synchronized void takeARest() {
        // We have to wait until all the passengers got out of the plane and have bags to collect in the plane hold
        try {
            wait();
        } catch (InterruptedException e) {}
    }

    /**
     * Porter has no more bags to collect from the plane hold
     */
    public synchronized boolean noMoreBagsToCollect() {
        return planeHold.empty();
    }

    /**
     * Porter tries to collect a bag from the plane hold
     */
    public synchronized BAG tryToCollectABag() {
        repositoryStub.removeLuggageInPlainHold();
        BAG b = planeHold.pop();
        return b;
    }

    /***** PASSENGER FUNCTIONS *********/


    public synchronized char whatShouldIDo(int id, boolean isFinalDestination, int numberOfLuggages) {
        this.passengersCount ++;
        char action = repositoryStub.passengerArrived(id, isFinalDestination, numberOfLuggages);
        if (this.passengersCount == SimulPar.PASSENGERS) {
            notifyAll(); // The last passenger to arrive will wake up the porter.
        }
        return action;
    }

    /***** MAIN THREAD *********/

    public synchronized void init_plane_hold(int flightNumber) {
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
     * Set if Porter's work day is over
     *
     */
    public synchronized void setPorterEndOfWork() {
        notifyAll();
    }
}
