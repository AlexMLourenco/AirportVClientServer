package sharedRegions;

import java.util.LinkedList;
import java.util.Queue;

import commonInfra.BAG;
import entities.Passenger;
import entities.PassengerStates;
import entities.PorterStates;
import stubs.RepositoryStub;

public class BaggageCollectionPoint implements SharedRegionInterface {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryStub repositoryStub;

    /**
     * Queue of all the bags
     * @serialField bags
     */
    private Queue<BAG> bags;

    /**
     * Signal that tells us if there are more bags at the plane hold or not
     * @serialField noMoreBagsInThePlaneHold
     */
    private boolean noMoreBagsInThePlaneHold;

    public BaggageCollectionPoint(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
        this.bags = new LinkedList<>();
        this.noMoreBagsInThePlaneHold = false;
    }

    /***** PORTER FUNCTIONS *********/

    /** DONE */
    public synchronized void carryItToAppropriateStore(BAG bag){
        this.bags.add(bag);
        repositoryStub.registerLuggageInConveyorBelt();
        notifyAll(); //Notify that a new bag is in the belt (this will wake up the passengers)
    }

    /** DONE */
    public synchronized void warningNoMoreBagsInThePlaneHold() {
        this.noMoreBagsInThePlaneHold = true;
        this.repositoryStub.setPorterState(PorterStates.WAITING_FOR_A_PLANE_TO_LAND);
        notifyAll();
    }

    /***** PASSENGER FUNCTIONS *********/

    /** DONE */
    public synchronized int goCollectBag(int id){
        int collected = 0;
        repositoryStub.setPassengerState(id, PassengerStates.AT_THE_LUGGAGE_COLLECTION_POINT);
        while(true){
            try{
                //wait until the porter places a bag in the belt or informs that there are no more bags in the plane hold
                wait();
                if (this.bags.size() != 0) {
                    if (this.bags.element().getPassenger() == id) {
                        this.repositoryStub.registerCollectedLuggage(this.bags.element().getPassenger());
                        this.bags.remove();
                        collected++;
                    }
                }
                if (noMoreBagsInThePlaneHold) break;

            }catch(InterruptedException e){}
        }
        return collected;
    }
}
