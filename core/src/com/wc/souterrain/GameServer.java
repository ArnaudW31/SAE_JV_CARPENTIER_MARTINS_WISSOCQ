package com.wc.souterrain;

import java.io.*;
import java.net.*;

public class GameServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                    System.out.println("Server is listening on port " + PORT);

                    while (true) {
                        Socket socket = serverSocket.accept();
                        System.out.println("New client connected");

                        new ClientHandler(socket).start();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true)
            ) {
                String text;

                do {
                    text = reader.readLine();
                    System.out.println("Received: " + text);
                    writer.println("Server: " + text);
                } while (!text.equals("bye"));

                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
