package sharedRegions;

import java.util.Stack;

import commonInfra.BAG;
import mainProject.SimulPar;
import stubs.RepositoryStub;

public class ArrivalLounge implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    Stack<BAG> planeHold;

    private int passengersCount;


    public ArrivalLounge(RepositoryStub repositoryStub) {
        this.repositoryStub = repositoryStub;
        this.planeHold = new Stack<>();
    }

    /** DONE **/
    public synchronized void takeARest() {
        try {
            wait();
        } catch (InterruptedException e) {}
    }

    /** DONE **/
    public synchronized boolean noMoreBagsToCollect() {
        return planeHold.empty();
    }

    /** DONE **/
    public synchronized BAG tryToCollectABag() {
        repositoryStub.removeLuggageInPlainHold();
        BAG b = planeHold.pop();
        return b;
    }


    /***** PASSENGER FUNCTIONS *********/

    /** DONE **/
    public synchronized char whatShouldIDo(int id, boolean isFinalDestination, int numberOfLuggages) {
        this.passengersCount ++;
        char action = repositoryStub.passengerArrived(id, isFinalDestination, numberOfLuggages);
        if (this.passengersCount == SimulPar.PASSENGERS) {
            notifyAll(); // The last passenger to arrive will wake up the porter.
        }
        return action;
    }

    /***** MAIN THREAD *********/
    /** DONE **/
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

    /** DONE **/
    public synchronized void setPorterEndOfWork() {
        notifyAll();
    }
}
