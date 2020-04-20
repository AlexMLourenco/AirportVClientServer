package sharedRegions;

import commonInfra.BAG;
import stubs.RepositoryStub;

import java.util.*;

public class TemporaryStorageArea implements SharedRegionInterface {

    private RepositoryStub repositoryStub;

    private Queue<BAG> bags;

    public TemporaryStorageArea(RepositoryStub repositoryStub){
        this.repositoryStub = repositoryStub;
        this.bags = new LinkedList<>();
    }

    public synchronized void carryItToAppropriateStore(BAG bag) {
        this.bags.add(bag);
        this.repositoryStub.registerLuggageInStoreRoom();
    }
}
