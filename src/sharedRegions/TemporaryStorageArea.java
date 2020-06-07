/**
 * <h1>Temporary Storage Area </h1>
 * TemporaryStorageArea Class implements the SharedRegionInterface and TemporaryStorageArea shared memory region.
 * In this shared region, is where the bags of the passengers that have a next leg will be temporary kept
 */
package sharedRegions;

import commonInfra.BAG;
import stubs.RepositoryStub;

import java.util.*;

public class TemporaryStorageArea implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    private Queue<BAG> bags;

    /**
     * TemporaryStorageArea constructor.
     * Creates a TemporaryStorageArea in repository Stub and a linked list to store bags
     * @param repositoryStub that corresponds to Stub Repository
     */
    public TemporaryStorageArea(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
        this.bags = new LinkedList<>();
    }

    /**
     * Bag registered in the Store Room
     * @return bag that will be carried by porter to the store room
     * */
    public synchronized void carryItToAppropriateStore(BAG bag) {
        this.bags.add(bag);
        this.repositoryStub.registerLuggageInStoreRoom();
    }
}
