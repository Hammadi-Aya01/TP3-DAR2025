package serverp;
import java.io.*;
import java.net.*;
import partage.operation;

public class ClientProcess extends Thread {
    private Socket socket;
    private int clientId;

    public ClientProcess(Socket socket, int clientId) {
        this.socket = socket;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("Bienvenue ! Vous êtes le client n°" + clientId);
            out.flush(); 
            operation op = (operation) in.readObject();
            double resultat = calculer(op);

            System.out.println("Client " + clientId + " → " +
                               op.getA() + " " + op.getOperateur() + " " + op.getB() +
                               " = " + resultat);

            out.writeObject(resultat);
            out.flush();

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Erreur avec le client " + clientId + " : " + e.getMessage());
        }
    }

    private double calculer(operation op) {
        double a = op.getA();
        double b = op.getB();
        char operateur = op.getOperateur();

        switch (operateur) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return (b != 0) ? a / b : Double.NaN;
            default:  return Double.NaN;
        }
    }
}
