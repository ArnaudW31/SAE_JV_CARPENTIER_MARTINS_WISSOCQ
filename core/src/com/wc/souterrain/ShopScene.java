package com.wc.souterrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.Random;

public class ShopScene extends Stage {
    
    private Player currentShopper;
    private Consommable pain;
    private Consommable saucisson_de_centaure;
    private Consommable vin;
    private Image playerImg;
    private Label playerGold;
    private Sound buySound;
    private Sound failSound;
    public boolean isShopping;
    
    public ShopScene(){
        isShopping = false;
    }
    
    public ShopScene(Camera camera, Player shopper){
        super(new ScreenViewport(camera));
        currentShopper = shopper;
        isShopping = true;
        
        Skin skin = new Skin(Gdx.files.internal("font/skinfile.json"));
        buySound = Gdx.audio.newSound(Gdx.files.internal("sounds/purchase.mp3"));
        failSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fail_purchase.mp3"));
        //3 images et 3 labels
        //sprite du joueur et ses pièces
        //bouton exit
        //label de bienvenue au dessus du marchand ??
        
        saucisson_de_centaure = new Consommable();
        saucisson_de_centaure.setName("Saucisson de Centaure");
        saucisson_de_centaure.setDescription("Rend 75 pv");

        pain = new Consommable();
        pain.setName("Pain");
        pain.setDescription("Rend 25 pv");

        vin = new Consommable();
        vin.setName("Vin");
        vin.setDescription("Rend 50 pv");
        
        
        
        ImageButton breadImg = new ImageButton(new SpriteDrawable(new Sprite(new Texture (Gdx.files.internal("bread.png")))));
        breadImg.setWidth(100);
        breadImg.setHeight(100);
        Label bPriceLbl = new Label("10",skin);
        
        ImageButton wineImg = new ImageButton(new SpriteDrawable(new Sprite(new Texture (Gdx.files.internal("wine.png")))));
        wineImg.setWidth(100);
        wineImg.setHeight(100);
        Label wPriceLbl = new Label("20",skin);
        
        ImageButton sausageImg = new ImageButton(new SpriteDrawable(new Sprite(new Texture (Gdx.files.internal("sausage.png")))));
        sausageImg.setWidth(100);
        sausageImg.setHeight(100);
        Label sPriceLbl = new Label("30",skin);
        
        playerImg = new Image(currentShopper.getSprite());
        playerImg.setScale(4f);
        Integer pg = currentShopper.getGold();
        playerGold = new Label(pg.toString(),skin);
        
        ImageButton exitButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("exit.png")))));
        exitButton.setWidth(300);
        exitButton.setHeight(300);
        
        breadImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(currentShopper.getGold()>=10){
                    buySound.play();
                    currentShopper.addToInventory(pain);
                    currentShopper.setGold(currentShopper.getGold()-10);
                    playerGold.setText(currentShopper.getGold());
                    GameClient.sendInformation(currentShopper.getName() + " achète 1 pain.");   

                }
                else{
                    failSound.play();
                }
            }
        });
        
        wineImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(currentShopper.getGold()>=20){
                    buySound.play();
                    currentShopper.addToInventory(vin);
                    currentShopper.setGold(currentShopper.getGold()-20);
                    playerGold.setText(currentShopper.getGold());
                    GameClient.sendInformation(currentShopper.getName() + " achète 1 bouteille de vin.");   

                }
                else{
                    failSound.play();
                }
            }
        });
        
        sausageImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(currentShopper.getGold()>=30){
                    buySound.play();
                    currentShopper.addToInventory(saucisson_de_centaure);
                    currentShopper.setGold(currentShopper.getGold()-30);
                    playerGold.setText(currentShopper.getGold());
                    GameClient.sendInformation(currentShopper.getName() + " achète 1 saucisson.");   
                }
                else{
                    failSound.play();
                }
            }
        });
        
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                isShopping = false;
                GameClient.sendInformation(currentShopper.getName() + " quitte la boutique");   
            }
        });
        
        if(currentShopper.getAI()>0){
            while(currentShopper.getGold()!=0){
                Random random = new Random();
                int chance = random.nextInt(3);
                switch (chance) {
                    case 0:
                        if(currentShopper.getGold()>=10){
                            buySound.play();
                            currentShopper.addToInventory(pain);
                            currentShopper.setGold(currentShopper.getGold()-10);
                            playerGold.setText(currentShopper.getGold());
                        }
                        break;
                    case 1:
                        if(currentShopper.getGold()>=20){
                            buySound.play();
                            currentShopper.addToInventory(vin);
                            currentShopper.setGold(currentShopper.getGold()-20);
                            playerGold.setText(currentShopper.getGold());
                        }
                        break;
                    case 2:
                        if(currentShopper.getGold()>=30){
                            buySound.play();
                            currentShopper.addToInventory(saucisson_de_centaure);
                            currentShopper.setGold(currentShopper.getGold()-30);
                            playerGold.setText(currentShopper.getGold());
                        }
                        break;
                }
            }
            isShopping = false;
        }
        
        addActor(breadImg);
        addActor(bPriceLbl);
        addActor(wineImg);
        addActor(wPriceLbl);
        addActor(sausageImg);
        addActor(sPriceLbl);
        addActor(playerImg);
        addActor(playerGold);
        addActor(exitButton);
    }
    
    public void changePlayerSprite(Player newShopper){
        currentShopper = newShopper;
        updateUI();
    }
    
    public void updateUI(){
        playerImg.setDrawable(new SpriteDrawable(new Sprite(currentShopper.getSprite())));
        playerGold.setText(currentShopper.getGold());
    }
    
    public void MoveUI(Camera camera){
        this.getActors().get(0).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 500 , camera.position.y - 200);
        this.getActors().get(1).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 500 , camera.position.y - 280);
        this.getActors().get(2).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 600 , camera.position.y - 200);
        this.getActors().get(3).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 600 , camera.position.y - 280);
        this.getActors().get(4).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 700 , camera.position.y - 200);
        this.getActors().get(5).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 700 , camera.position.y - 280);
        this.getActors().get(6).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 350 , camera.position.y - 250);
        this.getActors().get(7).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 350 , camera.position.y - 280);
        this.getActors().get(8).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 200 , camera.position.y - 100);
    }
}
