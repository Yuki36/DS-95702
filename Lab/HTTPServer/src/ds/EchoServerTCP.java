package ds;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;

public class EchoServerTCP {

    public static void main(String args[]){
        Socket clientSocket = null;
        try {
            int serverPort = 7777; // the server port we are using

            // Create a new server socket
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                /*
                 * Block waiting for a new connection request from a client.
                 * When the request is received, "accept" it, and the rest
                 * the tcp protocol handshake will then take place, making
                 * the socket ready for reading and writing.
                 */
                clientSocket = listenSocket.accept();
                // If we get here, then we are now connected to a client.

                // Set up "inFromSocket" to read from the client socket
                Scanner inFromSocket;
                inFromSocket = new Scanner(clientSocket.getInputStream());

                // Set up "outToSocket" to write to the client socket
                PrintWriter outToSocket;
                outToSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));

                /*
                 * Forever,
                 *   read a line from the socket
                 *   print it to the console
                 *   echo it (i.e. write it) back to the client
                 */
                String filePath = "";
                String currentPath = System.getProperty("user.dir");
                boolean getPath = false;
                while (inFromSocket.hasNextLine()) {
                    String data = inFromSocket.nextLine();
                    // Echo information print out
                    // System.out.println("Echoing: " + data);
                    if (data.startsWith("GET")) {
                        filePath = currentPath + data.substring(data.indexOf("/"), data.indexOf(" ", data.indexOf("/")));
                        System.out.println("file path is: " + filePath);
                        getPath = true;
                    }

                    if (getPath && data.equals("")) {
                        try {
                            File inputFile = new File(filePath);
                            Scanner fileReader = new Scanner((inputFile));
                            System.out.println("file opened");
                            outToSocket.println("HTTP/1.1 200 OK\n\n<!DOCTYPE html><html><body><h1>200 OK!</h1></body></html>");
                            outToSocket.flush();
                            while (fileReader.hasNextLine()) {
                                String nextLine = fileReader.nextLine();
                                System.out.println(nextLine);
                                outToSocket.write(nextLine);
                                outToSocket.flush();
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("file not found");
                            outToSocket.write("HTTP/1.0 404 File Not Found\n\n<!DOCTYPE html><html><body><h1>404! File not found</h1></body></html>");
                            outToSocket.flush();
                        }
                        clientSocket.close();
                    }
                }
            }

        // Handle exceptions
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());

        // If quitting (typically by you sending quit signal) clean up sockets
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // ignore exception on close
            }
        }
    }
}
