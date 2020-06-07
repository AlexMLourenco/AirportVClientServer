/**
 * <h1>Temporary Storage Area Stub  </h1>
 * TemporaryStorageAreaStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
import commonInfra.BAG;
/**
 * This stub class represents the Temporary Storage Area as it is needed in the
 * client side and communicates with the server side.
 */
public class TemporaryStorageAreaStub extends GenericStub{

    /**
     * TemporaryStorageAreaStub instatiation
     * @param hostname server hostname
     * @param port     server port
     *
     */
    public TemporaryStorageAreaStub(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * no more bags in the plane hold
     */
    public void  warningNoMoreBagsInThePlaneHold() {

    }

    /**
     * Porter Carries the the bags to the Temporary Storage Area
     * @param bag the bag to be carried
     */
    public void carryItToAppropriateStore(BAG bag) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.TEMPORARY_STORAGE_AREA_CARRY_IT_TO_APPROPRIATE_STORE);
        outMessage.setBag(bag);

        this.process(outMessage);

    }
    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.TEMPORARY_STORAGE_AREA_SIMULATION_FINISHED);

        this.process(outMessage);
    }
}
