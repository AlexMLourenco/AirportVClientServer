/**
 * <h1>Baggage Collection Point</h1>
 * BaggageCollectionPoint Class implements the SharedRegionInterface and Baggage Collection Point shared memory region.
 * In this shared region, is where the porter carries the bags
 * and the passengers pick them out
 *
 */
package sharedRegions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import commonInfra.BAG;
import entities.Passenger;
import entities.PassengerStates;
import entities.PorterStates;
import stubs.RepositoryStub;

public class BaggageCollectionPoint implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    private Queue<BAG> bags;

    private boolean noMoreBagsInThePlaneHold;
    /**
     * BaggageCollectionPoint constructor.
     * Creates a BaggageCollectionPoint in repository Stub
     * @param repositoryStub that corresponds to Stub Repository
     */
    public BaggageCollectionPoint(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
        this.bags = new LinkedList<>();
        this.noMoreBagsInThePlaneHold = false;
    }

    /**
     * sets the the variable noMoreBagsInThePlaneHold to false
     */
    public void cleanUp() {
        this.noMoreBagsInThePlaneHold = false;
    }

    /***** PORTER FUNCTIONS *********/

    /**
     * Porter carryItToAppropriateStore(BAG bag) method
     * @param bag Bag to be carried by porter that will be introduced in the Conveyor Belt
      */
    public synchronized void carryItToAppropriateStore(BAG bag){
        this.bags.add(bag);
        repositoryStub.registerLuggageInConveyorBelt();
        notifyAll(); //Notify that a new bag is in the belt (this will wake up the passengers)
    }

    /**
     * Porter warningNoMoreBagsInThePlaneHold() method
     * notifies that there's no more bags to carry
     */
    public synchronized void warningNoMoreBagsInThePlaneHold() {
        this.noMoreBagsInThePlaneHold = true;
        this.repositoryStub.setPorterState(PorterStates.WAITING_FOR_A_PLANE_TO_LAND);
        notifyAll();
    }

    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passenger goCollectBag(int id) method
     * @param id Passenger identifier
     * if the bag has the passenger id associated the passenger picks it up
     * @see InterruptedException
     */
    public synchronized int goCollectBag(int id){
        int collected = 0;
        repositoryStub.setPassengerState(id, PassengerStates.AT_THE_LUGGAGE_COLLECTION_POINT);
        while(true){
            try{
                //wait until the porter places a bag in the belt or informs that there are no more bags in the plane hold
                wait();
                if (this.bags.size() != 0) {
                    Stack<BAG> removeBags = new Stack<>();
                    for (BAG bag: this.bags) {
                        if (bag.getPassenger() == id) {
                            this.repositoryStub.registerCollectedLuggage(this.bags.element().getPassenger());
                            removeBags.push(bag);
                            collected++;
                        }
                    }
                    for (BAG bag: removeBags) {
                        this.bags.remove(bag);
                    }
                }

                if (noMoreBagsInThePlaneHold) break;

            }catch(InterruptedException e){}
        }
        return collected;
    }
}
