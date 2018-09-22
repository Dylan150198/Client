import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        {
            // BSN: 9789043026970
            Scanner userInput = new Scanner(System.in); // Create a scanner so that the user can enter the ISBN
            Socket clientSocket = new Socket("127.0.0.1", 1342); // Create a socket on the client side.
            Scanner output = new Scanner(clientSocket.getInputStream()); // Gets output from the server
            System.out.println("Enter the ISBN of the book.");
            String isbn = userInput.nextLine();

            PrintStream printToServer = new PrintStream(clientSocket.getOutputStream()); // Pass the ISBN to the server
            printToServer.println(isbn);

            // Storing the result of the server using the scanner output.
            String test = output.nextLine();
            System.out.println(test);
        }
    }
}

