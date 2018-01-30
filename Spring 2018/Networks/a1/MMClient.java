/** Client program for the MM app
 *
 *  @author YOUR FULL NAME GOES HERE
 *
 *  @version CS 391 - Spring 2018 - A1
 **/

import java.io.*;
import java.net.*;

public class MMClient {

    static String hostName = "localhost";  // name of server machine
    static int portNumber = 5555;          // port on which server listens
    static Socket socket = null;           // socket to server
    static DataInputStream in = null;      // input stream from server
    static DataOutputStream out = null;    // output stream to server
    static BufferedReader console = null;  // keyboard input stream

    /* connect to the server then repeatedly perform the 3 following steps:
           1. read the reply from the server
           2. input the user's next query string
           3. if the query is in {"Y","y","N","n"}, send it to the server as is
              else repeatedly prompt the user for a 4-character query String
                   that is then sent to the server
       until the server's reply is "    Thank you for playing!".
       The amount and format of the console output (e.g., user prompt, server
       replies) are imposed as part of the problem statement in the handout (as
       shown in the provided session trace).
     */
    public static void main(String[] args) {

        // to be completed

    }// main method

    /* open the necessary I/O streams and initializes the in, out, and console
       static variables; this method does not catch any exceptions.
     */
    static void openStreams() throws IOException {

        // to be completed

    }// openStreams method

    /* close ALL open I/O streams and sockets
     */
    static void close() {

        // to be completed

    }// close method
}// MMClient class
