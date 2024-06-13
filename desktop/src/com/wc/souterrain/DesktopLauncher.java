package com.wc.souterrain;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        // Démarrer le serveur dans un nouveau thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameClient.main(new String[0]);
            }
        }).start();

        // Configuration et lancement de l'application
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setWindowedMode(1000, 800);
        config.useVsync(true);
        config.setTitle("SAE_JV"); 
        config.setWindowIcon("bread.png");
        new Lwjgl3Application(new souterrain(), config);
    }
}