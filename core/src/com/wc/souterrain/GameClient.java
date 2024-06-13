package com.wc.souterrain;

import java.io.*;
import java.net.*;

public class GameClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true); BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connecté au serveur. " + in.readLine());

            String userInput;
            while ((userInput = consoleInput.readLine()) != null) {
                // Example of sending structured information
                if (userInput.startsWith("send")) {
                    String[] parts = userInput.split(" ", 2);
                    if (parts.length > 1) {
                        sendInformation(out, parts[1]);
                    } else {
                        System.out.println("Usage: send <information>");
                    }                  
                } else if (userInput.startsWith("stop")) {
                    break;
                } else {
                    // Send raw input to the server
                    out.println(userInput);
                }

                System.out.println("Réponse du serveur: " + in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendInformation(PrintWriter out, String information) {
        // Structure your message
        String structuredMessage = "INFO:" + information;
        out.println(structuredMessage);
    }
}
