import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        {
            // BSN that we're entering: 9789043026970
            Scanner userInput = new Scanner(System.in); // Create a scanner so that the user can enter the ISBN
            // For now we're running this locally, so we're passing along a local IP and port.
            // However, we could've used a scanner to ask the user to specify a server IP and port.
            Socket clientSocket = new Socket("127.0.0.1", 1355); // Create a socket on the client side.
            Scanner output = new Scanner(clientSocket.getInputStream()); // Gets output from the server
            System.out.println("Enter the ISBN of the book.");
            String isbn = userInput.nextLine(); // Store the IBSN into a variable
            PrintStream printToServer = new PrintStream(clientSocket.getOutputStream()); // Use a PrintStream to pass data to the server
            printToServer.println(isbn); // Pass the ISBN
            // Storing the result of the server using the output scanner.
            String result = output.nextLine();
            // Printing the server result
            System.out.println(result);
        }
    }
}

