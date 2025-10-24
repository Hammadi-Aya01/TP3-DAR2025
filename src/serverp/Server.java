package serverp;
import java.io.*;
import java.net.*;

public class Server {
    private static int clientCount = 0; // Compteur de clients

    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serveur = new ServerSocket(port)) {
            System.out.println("Serveur en attente sur le port " + port + "...");

            while (true) {
                Socket socket = serveur.accept();
                clientCount++;
                System.out.println("Client " + clientCount + " connecté depuis : " + socket.getRemoteSocketAddress());

                new ClientProcess(socket, clientCount).start();
            }

        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}
