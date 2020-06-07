/**
 * <h1>Baggage Collection Point Stub </h1>
 * BaggageCollectionPointStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
import commonInfra.BAG;
/**
 * This stub class represents the Baggage Collection Point as it is needed in the
 * client side and communicates with the server side.
 */
public class BaggageCollectionPointStub extends GenericStub{
    /**
     * BaggageCollectionPointStub instatiation
     * @param hostname server hostname
     * @param port     server port
     */
    public BaggageCollectionPointStub(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * Indicates that there's no more bags in the plane hold
     */
    public void warningNoMoreBagsInThePlaneHold() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_COLLECTION_POINT_WARNING_NO_MORE_BAGS_IN_PLANE_HOLD);

        this.process(outMessage);
    }
    /**
     * Porter Carries the bag to the Baggage collection point
     * @param bag the bag that the porter is carrying
     */
    public void carryItToAppropriateStore(BAG bag) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_COLLECTION_POINT_CARRY_IT_TO_APPROPRIATE_STORE);
        outMessage.setBag(bag);

        this.process(outMessage);

    }
    /**
     * Passengers Collects the bag in baggage collection point
     * @param identifier int that identifies the passenger and his bag
     */
    public int goCollectBag(int identifier) {
        Message outMessage, inMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_COLLECTION_POINT_GO_COLLECT_BAG);
        outMessage.setIdentifier(identifier);

        inMessage = this.process(outMessage);

        return inMessage.getIntValue();
    }

    /**
     * no more bags to collect
     */
    public void clean_up() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_COLLECTION_POINT_CLEAN_UP);

        this.process(outMessage);
    }
    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_COLLECTION_POINT_SIMULATION_FINISHED);

        this.process(outMessage);
    }

}
