/**
 * <h1>Client Comunication</h1>
 * The ClientCom program implements methods to establish and close connection
 * between a client and the server through a socket.
 */

package commonInfra;

import commonInfra.GenericCom;

import java.io.*;
import java.net.*;

public class ClientCom extends GenericCom {

    private Socket socket = null;

    private String hostname = null;
    private int port;
    /**
     *  ClientCom constructor.
     * @param hostname This is the parameter to set the hostname in the server socket
     * @param port     This is the parameter to set the port in the server socket
     */
    public ClientCom (String hostname, int port)
    {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * This method is used to check if a connection between the Client and the server is established
     * A socket is created and it tries to connect to serverAddress
     * @param args Unused.
     * @return {@code true}  if the connection has been established
     *         otherwise {@code false}  (the socket could not connect to server address...) .
     * @see IOException, UnknownHostException , ConnectException, SocketTimeoutException
     *
     */
    public boolean open ()
    {
        String errorMessage = Thread.currentThread ().getName () + ": ";
        boolean keepAlive = false;
        SocketAddress serverAddress = new InetSocketAddress(hostname, port);

        try {
            socket = new Socket();
            socket.connect (serverAddress);
        }
        catch (Exception ex) {
            if (ex instanceof UnknownHostException ) {
                errorMessage = errorMessage.concat(String.format("The destination server is Unknown %s - %d", hostname,port));
            } else if (ex instanceof NoRouteToHostException ) {
                errorMessage = errorMessage.concat(String.format("No Route to Host %s - %d", hostname,port));
            } else if (ex instanceof ConnectException  ) {
                errorMessage = errorMessage.concat(String.format("The server is not replying %s - %d", hostname,port));
                if (ex.getMessage ().equals ("Connection refused")) keepAlive = true;
            } else if  (ex instanceof SocketTimeoutException  ) {
                errorMessage = errorMessage.concat(String.format("Socket Timeout to host %s - %d", hostname,port));
                keepAlive = true;
            } else if  (ex instanceof IOException  ) {
                errorMessage = errorMessage.concat(String.format("Unspecified error connecting to host %s - %d", hostname,port));
            }

            if (keepAlive) return true;

            System.out.println(errorMessage);
            ex.printStackTrace ();
            System.exit (1);
        }


        try {
            this.setOut(new ObjectOutputStream (socket.getOutputStream ()));
        }
        catch (IOException e)
        {
            errorMessage = errorMessage.concat(String.format("Could not open OUT channel on the socket %s - %d", hostname,port));
            System.out.println(errorMessage);
            e.printStackTrace ();
            System.exit (1);
        }

        try {
            this.setIn(new ObjectInputStream (socket.getInputStream ()));
        }
        catch (IOException e)
        {
            errorMessage = errorMessage.concat(String.format("Could not open IN channel on the socket %s - %d", hostname,port));
            System.out.println(errorMessage);
            e.printStackTrace ();
            System.exit (1);
        }

        return (keepAlive);
    }

    /**
     * This method is used to close conection between the socket and the server
     * @param args Unused.
     * @return Nothing.
     */
    public void close () {
        String errorMessage = Thread.currentThread ().getName () + ": ";
        try {
            this.getIn().close();
        } catch (IOException e) {
            errorMessage.concat("Couldn't close IN channel of the socket!");
            System.out.println(errorMessage);
            e.printStackTrace ();
            System.exit (1);
        }

        try {
            this.getOut().close();
        }
        catch (IOException e) {
            errorMessage.concat("Couldn't close OUT channel of the socket!");
            System.out.println(errorMessage);
            e.printStackTrace ();
            System.exit (1);
        }

        try {
            socket.close();
        }
        catch (IOException e) {
            errorMessage.concat("Couldn't close socket!");
            System.out.println(errorMessage);
            e.printStackTrace ();
            System.exit (1);
        }
    }


}
