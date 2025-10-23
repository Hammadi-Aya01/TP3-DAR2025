package serverp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int clientCount = 0;

    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur en attente sur le port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                int currentClientNumber;
                synchronized (Server.class) {
                    clientCount++;
                    currentClientNumber = clientCount;
                }
                System.out.println("Client " + currentClientNumber + " connecté depuis : " + clientSocket.getRemoteSocketAddress());
                ClientProcess clientProcess = new ClientProcess(clientSocket, currentClientNumber);
                new Thread(clientProcess).start();
            }
        } catch (IOException e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }
}