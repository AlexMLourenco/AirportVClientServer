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
     * @param args unused
     * @return the message type
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /** Updates the message type.
     * @param in The message type to be set.
     * @return Nothing
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * Returns the Entity State.
     * @param args unused
     * @return the message type
     */
    public StateInterface getEntityState() {
        return entityState;
    }

    /**
     * Updates the Entity State.
     * @param entityState
     * @return Nothing
     */
    public void setEntityState(StateInterface entityState) {
        this.entityState = entityState;
    }

    /**
     * Returns the Identifier.
     * @param args unused
     * @return the Identifier of the message
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Updates the Identifier.
     * @param identifier The identifier of the message
     * @return Nothing
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the Integer value.
     * @param args unused
     * @return the int value of the message
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Updates the Identifier.
     * @param identifier The identifier of the message
     * @return Nothing
     */
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    /**
     * Returns the Boolean value of... .
     * @param args unused
     * @return boolean value it can be {@code true} if .... otherwise {@code false}
     */
    public boolean getBooleanValue() {
        return booleanValue;
    }

    /**
     * Updates the boolean value of.
     * @param booleanValue
     * @return Nothing
     */
    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    /**
     * Returns a Bag
     * @param args unused
     * @return bag
     */
    public BAG getBag() {
        return bag;
    }

    /**
     * Updates the bag.
     * @param bag a Bag
     * @return Nothing
     */
    public void setBag(BAG bag) {
        this.bag = bag;
    }

    /**
     * Returns the char Value
     * @param args unused
     * @return char Value
     */
    public char getCharValue() {
        return charValue;
    }

    /**
     * Updates char Value.
     * @param charvalue
     * @return Nothing
     */
    public void setCharValue(char charValue) {
        this.charValue = charValue;
    }

    /**
     * Returns the arrray ....
     * @param args unused
     * @return intArray Array of integers
     */
    public int[][] getIntArray() {
        return intArray;
    }

    /**
     * Updates Array.
     * @param intArray
     * @return Nothing
     */
    public void setIntArray(int[][] intArray) {
        this.intArray = intArray;
    }

    /**
     * Returns the arrray ....
     * @param args unused
     * @return booleanArray Array of booleans
     */
    public boolean[][] getBooleanArray() {
        return booleanArray;
    }

    /**
     * Updates Array.
     * @param intArray
     * @return Nothing
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
