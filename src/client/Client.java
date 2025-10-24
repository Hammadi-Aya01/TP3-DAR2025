package client;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import partage.operation;
public class Client {
    public static void main(String[] args) {
        int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket("localhost", SERVER_PORT);
            System.out.println("Connecté au serveur.");

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);

            String message = (String) in.readObject();
            System.out.println(message);

            System.out.print("Entrez une opération (ex: 12 + 4) : ");
            double a = sc.nextDouble();
            char op = sc.next().charAt(0);
            double b = sc.nextDouble();

            operation operation = new operation(a, op, b);
            out.writeObject(operation);
            out.flush();

            double resultat = (double) in.readObject();
            System.out.println("Résultat reçu du serveur : " + resultat);

            in.close();
            out.close();
            socket.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
