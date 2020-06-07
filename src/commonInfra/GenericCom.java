/**
 * <h1>Generic Comunication</h1>
 * The GenericCom class implements the methods to
 * exchange messages between server and client
 */


package commonInfra;

import java.io.*;

public class GenericCom {
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    /**
     * GenericCom constructor.
     */
    public GenericCom() {

    }
    /**
     * This method is used to read an Object
     * A socket is created and it tries to connect to serverAddress
     * @return Object the object that will be read.
     * @see IOException
     * @see InvalidClassException
     * @see ClassNotFoundException
     *
     */
    public Object readObject (){
        String errorMessage = Thread.currentThread ().getName () + ": ";
        Object inObject = null;

        try {
            inObject = in.readObject ();
        } catch (Exception ex) {
            if (ex instanceof InvalidClassException) {
                errorMessage = errorMessage.concat("Could not deserialize the object!");
            } else if (ex instanceof IOException) {
                errorMessage = errorMessage.concat("Error reading from the IN channel of the socket!");
            } else if (ex instanceof ClassNotFoundException) {
                errorMessage = errorMessage.concat("Unknown data type!");
            }
            System.out.println(errorMessage);
            System.exit (1);
        }

        return inObject;
    }
    /**
     * This method is used to write an Object
     * ...
     * @return Object the object that will be read.
     * @see InvalidClassException, NotSerializableException , IOException
     *
     */
    public void writeObject (Object outObject) {
        String errorMessage = Thread.currentThread ().getName () + ": ";
        try {
            out.writeObject (outObject);
        } catch (Exception ex) {
            if (ex instanceof  InvalidClassException ) {
                errorMessage = errorMessage.concat("Could not serialize object!");
            } else if (ex instanceof NotSerializableException) {
                errorMessage = errorMessage.concat("The object is not serializable!");
            } else if (ex instanceof IOException) {
                errorMessage = errorMessage.concat("Error reading from the OUT channel of the socket!");
            }
            System.out.println(errorMessage);
            ex.printStackTrace ();
            System.exit (1);
        }
    }

    /** Returns the input object.
     * @return Input Object
     */
    public ObjectInputStream getIn() {
        return in;
    }

    /** Updates the input object.
     * @param in The object to be set.
     */
    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    /** Returns the output object.
     * @return Output Object
     */
    public ObjectOutputStream getOut() {
        return out;
    }

    /** Updates the output object.
     * @param out The object to be set.
     */
    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }
}
