/**
 * <h1>Message</h1>
 * The Message class implements the methods to
 * process messages between server and client
 */



package commonInfra;

import commonInfra.BAG;
import commonInfra.MessageType;
import entities.StateInterface;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private MessageType messageType;
    private StateInterface entityState;

    private int identifier;

    private int intValue;
    private boolean booleanValue;
    private char charValue;

    private int[][] intArray;
    private boolean[][] booleanArray;

    private BAG bag;

    /**
     * GenericCom constructor.
     */
    public Message() {

    }
    /**
     * Returns the Message type.
     * @return the message type
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /** Updates the message type.
     * @param messageType the message type to be set.
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * Returns the Entity State.
     * @return the message type
     */
    public StateInterface getEntityState() {
        return entityState;
    }

    /**
     * Updates the Entity State.
     * @param entityState
     */
    public void setEntityState(StateInterface entityState) {
        this.entityState = entityState;
    }

    /**
     * Returns the Identifier.
     * @return the Identifier of the message
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Updates the Identifier.
     * @param identifier The identifier of the message
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the Integer value.
     * @return the int value of the message
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Updates the Identifier.
     * @param intValue The identifier of the message
     */
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    /**
     * Returns the Boolean value of... .
     * @return boolean value it can be {@code true} if .... otherwise {@code false}
     */
    public boolean getBooleanValue() {
        return booleanValue;
    }

    /**
     * Updates the boolean value of.
     * @param booleanValue
     */
    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    /**
     * Returns a Bag
     * @return bag
     */
    public BAG getBag() {
        return bag;
    }

    /**
     * Updates the bag.
     * @param bag a Bag
     */
    public void setBag(BAG bag) {
        this.bag = bag;
    }

    /**
     * Returns the char Value
     * @return char Value
     */
    public char getCharValue() {
        return charValue;
    }

    /**
     * Updates char Value.
     * @param charValue
     */
    public void setCharValue(char charValue) {
        this.charValue = charValue;
    }

    /**
     * Returns the arrray ....
     * @return intArray Array of integers
     */
    public int[][] getIntArray() {
        return intArray;
    }

    /**
     * Updates Array.
     * @param intArray
     */
    public void setIntArray(int[][] intArray) {
        this.intArray = intArray;
    }

    /**
     * Returns the arrray ....
     * @return booleanArray Array of booleans
     */
    public boolean[][] getBooleanArray() {
        return booleanArray;
    }

    /**
     * Updates Array.
     * @param booleanArray
     */
    public void setBooleanArray(boolean[][] booleanArray) {
        this.booleanArray = booleanArray;
    }

    /** Extended version of {@link #toString()}.
     * @return A formatted string representing the message type.
     */
    @Override
    public String toString() {
        return "Message: " + this.getMessageType();
    }
}
