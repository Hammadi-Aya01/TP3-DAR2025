package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String HOST = "localhost";
        int PORT = 12345;

        try (
            Socket socket = new Socket(HOST, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connecté au serveur.");
            String welcome = in.readLine();
            System.out.println(welcome);
            System.out.print("Tapez un message à envoyer : ");
            String msg = scanner.nextLine();
            out.println(msg);
            String reply = in.readLine();
            System.out.println(reply);

        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}