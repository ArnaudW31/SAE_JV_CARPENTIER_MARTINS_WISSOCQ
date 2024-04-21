/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
package com.wc.souterrain;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.Viewport;
/**
 *
 * @author carpe
 
public class CombatUi {
    private Stage stage;
    private Skin skin;
    private Table table;

    public void CombatUI(Viewport viewport) {
        Viewport viewport = new FitViewport(500, 600); // Remplacez width et height par les dimensions souhaitées.
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("uiskin.json")); // Utilisez le skin que vous préférez.


        // Créez une table pour organiser les éléments de l'UI.
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Créez des boutons pour les choix du joueur.
        TextButton attackButton = new TextButton("Attaquer", skin);
        TextButton useItemButton = new TextButton("Utiliser un objet", skin);

        // Ajoutez des listeners aux boutons pour gérer les actions du joueur.
        attackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Gérez l'attaque ici.
            }
        });

        useItemButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Gérez l'utilisation d'un objet de l'inventaire ici.
            }
        });

        // Ajoutez les boutons à la table.
        table.add(attackButton).pad(10);
        table.add(useItemButton).pad(10);
    }

    public void draw() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
*/
