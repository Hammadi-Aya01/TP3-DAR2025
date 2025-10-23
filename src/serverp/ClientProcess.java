package serverp;

import java.io.*;
import java.net.Socket;

public class ClientProcess implements Runnable {
    private Socket socket;
    private int clientNumber;

    public ClientProcess(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("Vous êtes le client n°" + clientNumber);
            String inputLine = in.readLine();
            if (inputLine != null) {
                System.out.println("Client " + clientNumber + " dit : " + inputLine);
                out.println("Message reçu par le serveur : " + inputLine);
            }

        } catch (IOException e) {
            System.err.println("Erreur avec le client " + clientNumber + " : " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}