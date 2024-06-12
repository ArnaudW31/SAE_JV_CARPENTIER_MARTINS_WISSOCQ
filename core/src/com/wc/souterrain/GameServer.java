package com.wc.souterrain;



import java.io.*;
import java.net.*;

public class GameServer {
    private static final int PORT = 12345;
    private static final int MAX_CLIENTS = 4;
    private static int clientCount = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Le serveur est en marche...");

            while (clientCount < MAX_CLIENTS) {
                Socket clientSocket = serverSocket.accept();
                clientCount++;
                String clientName = "Joueur" + clientCount;
                System.out.println(clientName + " est connecté.");

                // Démarrer un nouveau thread pour gérer le client
                new ClientHandler(clientSocket, clientName).start();
            }

            System.out.println("Nombre maximum de clients atteint. Le serveur n'acceptera plus de nouveaux clients.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private String clientName;

    public ClientHandler(Socket socket, String name) {
        this.clientSocket = socket;
        this.clientName = name;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Bienvenue " + clientName + "! Vous êtes connecté au serveur.");

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(clientName + ": " + message);
                out.println("Message reçu: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(clientName + " s'est déconnecté.");
        }
    }
}
