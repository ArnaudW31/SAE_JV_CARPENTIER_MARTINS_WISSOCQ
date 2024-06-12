package com.wc.souterrain;

import common.Message;
import com.wc.souterrain.Player;
import com.wc.souterrain.Consommable;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private ObjectOutputStream out;
    private Player player;

    public static void main(String[] args) {
        GameClient client = new GameClient();
        client.start();
    }

    public void start() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            this.out = out;
            player = new Player();
            player.setName("arnaud");
            player.setDescription("C'est un mec cool");
            player.setHp(1000);
            player.setDmg(100);
            player.setDef(50);
            player.setCrit(50);
            player.setSpeed(100);
            player.setPrecision(100);

            // Initialiser les consommables
            Consommable tête_de_méduse = new Consommable();
            tête_de_méduse.setName("Tête de méduse");
            tête_de_méduse.setDescription("Réduit la défense de la cible de 20%");

            Consommable saucisson_de_centaure = new Consommable();
            saucisson_de_centaure.setName("Saucisson de Centaure");
            saucisson_de_centaure.setDescription("Rend 75 pv");

            player.addToInventory(tête_de_méduse);
            player.addToInventory(saucisson_de_centaure);

            new Thread(new Receiver(in)).start();

            System.out.println("Jeu démarré. Tapez '1' pour afficher l'inventaire, '2' pour utiliser un consommable:");
            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                if (userMessage.equals("1")) {
                    player.showInventory();
                } else if (userMessage.equals("2")) {
                    System.out.println("Quel consommable utiliser ? (0 pour le premier, 1 pour le second, etc.)");
                    int index = Integer.parseInt(userInput.readLine());
                    Consommable item = player.getInventory().get(index);
                    // Envoyer l'action au serveur
                    out.writeObject(new Message("use", item.getName()));
                    System.out.println("Utilisation de " + item.getName());
                } else {
                    out.writeObject(new Message("chat", userMessage));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Receiver implements Runnable {
        private ObjectInputStream in;

        public Receiver(ObjectInputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                Message serverMessage;
                while ((serverMessage = (Message) in.readObject()) != null) {
                    System.out.println("Message du serveur: " + serverMessage.getContent());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
