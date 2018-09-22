import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    // This is the client
    public static void main(String[] args) {
        // Print an error message, if there are no two command line arguments found.
        if (args.length != 2) {
            System.err.println(
                    "Usage: Client <host name> <port number>");
            System.exit(1);
        }
        // Set the hostname to the first command line argument found
        String hostName = args[0];

        // Set the portnumber to the second command line argument found
        int portNumber = Integer.parseInt(args[1]);
        try
        {
            // https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
            // Step 1: Create a socket to the server
            Socket sock = new Socket(hostName, portNumber);
            // Step 2: Open an output stream to the socket.
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            // Step 3: Open an input stream to the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            // Step 4: To get the server's response, the client reads from the BufferedReader object stdIn
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            // Step 5: We're gonna send our input.
            String fromServer;
            String fromUser;

            while ((fromServer = stdIn.readLine()) != null)
            {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                {
                    break;
                }
                fromUser = stdIn.readLine();
                if (fromUser != null)
                {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
                // The loop reads a line at a time from the standard input stream and,
                // immediately sends it to the server by writing it to the PrintWriter connected to the socket:

                //reads a line of information from the BufferedReader connected to the socket.
                //The readLine method waits until the server echoes the information back to the client.
                //When readline returns, the client prints the information to the standard output.
            }
        }
        // Error handling if the host is not found.
        catch (UnknownHostException e) {
            System.err.println("Host: " + hostName + "Not found");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

