/** Client program for the MM app
 *
 *  @author Justin Espiritu
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

        String query, reply;

        try {
            socket = new Socket(hostName, portNumber);
            System.out.println("Connected to server: " + socket);
            openStreams();
            
            query = "start";
            out.writeUTF(query);
            while (true){
                reply = in.readUTF();
                System.out.println(reply);
                if(reply.equals("    Thank you for playing!")){ break; }
                query = console.readLine();
                out.writeUTF(query);
            }
            close();
        } catch (UnknownHostException e){
            System.err.println("Unknown host: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O error when connecting to " + hostName);
            System.exit(1);
        }

    }// main method

    /* open the necessary I/O streams and initializes the in, out, and console
       static variables; this method does not catch any exceptions.
     */
    static void openStreams() throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        console = new BufferedReader(new InputStreamReader(System.in));
    }// openStreams method

    /* close ALL open I/O streams and sockets
     */
    static void close() {
        try {
            if (console != null) {console.close(); }
            if (in != null)      {in.close();      }
            if (out != null)     {out.close();     }
            if (socket != null)  {socket.close();  }
        } catch (IOException e) {
            System.err.println("Error in close(): " + e.getMessage());
        }
    }// close method
}// MMClient class
