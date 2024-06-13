package com.wc.souterrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.Random;

public class MapScene extends Stage {
    
    private Label UIName;
    private Skin skin;
    private Image weaponPic;
    public static ArrayList<String> dispo; 
        
    public MapScene(Camera camera){
        super(new ScreenViewport(camera));
        skin = new Skin(Gdx.files.internal("font/skinfile.json"));
        //table = new Table(skin);
        //table.setWidth(getWidth());
        //table.setHeight(getHeight());
        
        //table.align(Align.center|Align.top);
        //table.setPosition(0, Gdx.graphics.getHeight());

        UIName = new Label("",skin);
        UIName.setHeight(100);
        UIName.setWidth(100);
        UIName.setName("startButton");
        
        dispo = new ArrayList<>();
        dispo.add("bident");
        dispo.add("trident");
        dispo.add("shield");
        dispo.add("dague");
        
        Image holder = new Image(new Texture (Gdx.files.internal("UIholder.png")));
        holder.setScale(5);
        
        Image attackImg = new Image(new Texture (Gdx.files.internal("dmgIcon.png")));
        attackImg.setScale(1.2f);
        Label attackLbl = new Label("10",skin);
        
        Image defenseImg = new Image(new Texture (Gdx.files.internal("defIcon.png")));
        Label defenseLbl = new Label("20",skin);
        
        Image critImg = new Image(new Texture (Gdx.files.internal("crtIcon.png")));
        critImg.setScale(1.2f);
        Label critLbl = new Label("30",skin);
        
        Image speedImg = new Image(new Texture (Gdx.files.internal("spdIcon.png")));
        Label speedLbl = new Label("40",skin);
        
        Image precisionImg = new Image(new Texture (Gdx.files.internal("prcIcon.png")));
        Label precisionLbl = new Label("50",skin);
        
        Image moneyImg = new Image(new Texture (Gdx.files.internal("mnyIcon.png")));
        Label moneyLbl = new Label("60",skin);
        
        Label timeRemainingLabel = new Label("Temps restant : ", skin);
        timeRemainingLabel.setWidth(100);
        timeRemainingLabel.setHeight(100);
        
        weaponPic = new Image(new Texture (Gdx.files.internal("fist.png")));
        weaponPic.setScale(3);

        addActor(holder);
        addActor(UIName);
        addActor(attackImg);
        addActor(attackLbl);
        addActor(defenseImg);
        addActor(defenseLbl);
        addActor(critImg);
        addActor(critLbl);
        addActor(speedImg);
        addActor(speedLbl);
        addActor(precisionImg);
        addActor(precisionLbl);
        addActor(moneyImg);
        addActor(moneyLbl);
        addActor(timeRemainingLabel);
        addActor(weaponPic);
    }
    
    public void setPlayerName(String newName){
        UIName.setText(newName);
    }
    
    public void updateTimeRemaining(float timeRemaining) {
        Label label = (Label)this.getActors().get(14);
        label.setText("Temps restant : " + (int)(timeRemaining/2) + " secondes");
    }
    
    public void timeDisappear() {
        Label label = (Label)this.getActors().get(14);
        label.setText("Finissez le tour en cours.");
    }
    
    public void updateStats(int att,int def,int crt,int spd,int prc, int mny, String weapon)
    {
        Label label = (Label)this.getActors().get(3);
        label.setText(att);
        label = (Label)this.getActors().get(5);
        label.setText(def);
        label = (Label)this.getActors().get(7);
        label.setText(crt);
        label = (Label)this.getActors().get(9);
        label.setText(spd);
        label = (Label)this.getActors().get(11);
        label.setText(prc);
        label = (Label)this.getActors().get(13);
        label.setText(mny);
        weaponPic.setDrawable(new SpriteDrawable(new Sprite(new Texture (Gdx.files.internal(weapon+".png")))));
        
    }
    
    public void MoveUI(Camera camera){
        UIName.setPosition(camera.position.x - 40 , camera.position.y+ Gdx.graphics.getHeight()/2 - 80);
        this.getActors().get(0).setPosition(camera.position.x - Gdx.graphics.getWidth()/2, camera.position.y - Gdx.graphics.getHeight()/2);
        this.getActors().get(2).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 + 60 , camera.position.y + 80);
        this.getActors().get(3).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 10 , camera.position.y + 80);
        this.getActors().get(4).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 + 60 , camera.position.y + 40);
        this.getActors().get(5).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 10 , camera.position.y + 40);
        this.getActors().get(6).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 + 60 , camera.position.y );
        this.getActors().get(7).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 10 , camera.position.y );
        this.getActors().get(8).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 + 60 , camera.position.y - 40);
        this.getActors().get(9).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 10 , camera.position.y - 40);
        this.getActors().get(10).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 + 60 , camera.position.y - 80);
        this.getActors().get(11).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 10 , camera.position.y - 80);
        this.getActors().get(12).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 + 60 , camera.position.y - 120);
        this.getActors().get(13).setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 10 , camera.position.y - 120);
        this.getActors().get(14).setPosition(camera.position.x - 260, camera.position.y+ Gdx.graphics.getHeight()/2 - 820);
        weaponPic.setPosition(camera.position.x + Gdx.graphics.getHeight()/2 - 100 , camera.position.y - 360);
    }
    
    public void obtainWeapon(Player pl) {
        
        if(pl.getWeapon().equals("fist")){
            Random random = new Random();
            int weapon = random.nextInt(dispo.size());
            switch (dispo.get(weapon)) {
                case "trident":
                    dispo.remove(weapon);
                    pl.setWeapon("trident");
                    pl.setMaxHp(pl.getMaxHp()+100);
                    pl.setDmg(pl.getDmg()+15);
                    pl.setPierceArmor(25);
                    this.updateStats(pl.getDmg(),pl.getDef(),pl.getCrit() , pl.getSpeed(), pl.getPrecision(), pl.getGold(),"trident");
                    if(GameClient.SERVER_ADDRESS != null)
                        GameClient.sendInformation(pl.getName() + " Obtient : Trident");  
                    break;

                case "bident":
                    dispo.remove(weapon);
                    pl.setWeapon("bident");
                    pl.setDmg(pl.getDmg()+20);
                    pl.setInstantKillChance(3);
                    pl.setCrit(40);
                    this.updateStats(pl.getDmg(),pl.getDef(),pl.getCrit() , pl.getSpeed(), pl.getPrecision(), pl.getGold(),"bident");
                    if(GameClient.SERVER_ADDRESS != null)
                        GameClient.sendInformation(pl.getName() + " Obtient : Bident");                
                    break;
                    
                case "shield":
                    dispo.remove(weapon);
                    pl.setWeapon("shield");
                    pl.setMaxHp(pl.getMaxHp()+50);
                    pl.setDef(pl.getDef()+50);
                    pl.setReflectChance(15);
                    this.updateStats(pl.getDmg(),pl.getDef(),pl.getCrit() , pl.getSpeed(), pl.getPrecision(), pl.getGold(),"shield");
                    if(GameClient.SERVER_ADDRESS != null)
                        GameClient.sendInformation(pl.getName() + " Obtient : Bouclier");                 
                    break;
                    
                case "dague":
                    dispo.remove(weapon);
                    pl.setWeapon("dague");
                    pl.setDmg(pl.getDmg()+40);
                    pl.setSpeed(pl.getSpeed()+50);
                    pl.setCrit(20);
                    this.updateStats(pl.getDmg(),pl.getDef(),pl.getCrit() , pl.getSpeed(), pl.getPrecision(), pl.getGold(),"dague");
                    if(GameClient.SERVER_ADDRESS != null)
                        GameClient.sendInformation(pl.getName() + " Obtient : Dague");                 
                    break;
            }
        }
    }
}