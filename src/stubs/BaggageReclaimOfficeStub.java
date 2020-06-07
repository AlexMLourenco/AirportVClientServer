/**
 * <h1>Baggage Reclaim Office Stub </h1>
 * BaggageReclaimOfficeStub Class extends the GenericStub Class and implements the methods that represents
 * the actions that can happen in this shared region
 */
package stubs;

import commonInfra.Message;
import commonInfra.MessageType;
/**
 * This stub class represents the Baggage Reclaim Office as it is needed in the
 * client side and communicates with the server side.
 */
public class BaggageReclaimOfficeStub extends GenericStub{
    /**
     * BaggageReclaimOfficeStub instatiation
     * @param hostname server hostname
     * @param port     server port
     */
    public BaggageReclaimOfficeStub(String hostname, int port) {
        super(hostname, port);
    }

    /**
     * Passenger reports the missing bag
     * @param identifier identifies the passenger and its bag
     */
    public void reportMissingBag(int identifier) {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_RECLAIM_OFFICE_RECLAIM_MISSING_BAG);
        outMessage.setIdentifier(identifier);

        this.process(outMessage);

    }

    /**
     * Simulation reaches the end
     */
    public void setSimulationFinished() {
        Message outMessage;

        outMessage= new Message();
        outMessage.setMessageType(MessageType.BAGGAGE_RECLAIM_OFFICE_SIMULATION_FINISHED);

        this.process(outMessage);
    }
}
