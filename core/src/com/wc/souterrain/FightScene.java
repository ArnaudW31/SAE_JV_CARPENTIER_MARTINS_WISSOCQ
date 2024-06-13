package com.wc.souterrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class FightScene extends Stage {
    
    private Entity fighterA;
    private Entity fighterB;
    private Entity currentAttacker;
    private ImageButton useConsommable;
    private ImageButton useWeapon;
    private Image spriteEnnemy1;
    private SequenceAction animF1;
    private SequenceAction animF2;
    private Image spriteEnnemy2;
    private Label ennemyHealth1;
    private Label ennemyHealth2;
    private Label lostHealthF1;
    private Label lostHealthF2;
    private ParallelAction lossF1;
    private ParallelAction lossF2;
    private Skin skin;
    public boolean isFighting;
    private boolean waitforattack;
    
    public FightScene(){ // on initialise une scene vide pour plus tard vérifier si on est dans une interface de combat ou de shop
        isFighting = false;
        fighterB = new Entity();
    }
    
    public FightScene(Camera camera, Entity Firstfighter, Entity Secondfighter){
        super(new ScreenViewport(camera));
        
        isFighting = true;
        
        int xVoulu = -1500;
        int yVoulu = 400;
        
        fighterA = Firstfighter;
        fighterB = Secondfighter;

        skin = new Skin(Gdx.files.internal("font/skinfile.json"));
        
        MoveToAction attackF1 = new MoveToAction();
        attackF1.setPosition(xVoulu-300, yVoulu-100);
        attackF1.setDuration(0.5f);
        
        MoveToAction reculF1 = new MoveToAction();
        reculF1.setPosition(xVoulu+200, yVoulu-100);
        reculF1.setDuration(1f);
        
        animF1 = new SequenceAction(attackF1,reculF1);
        
        
        DelayAction dlF2 = new DelayAction(2f);
        MoveToAction attackF2 = new MoveToAction();
        attackF2.setPosition(xVoulu+200, yVoulu-100);
        attackF2.setDuration(0.5f);
        
        MoveToAction reculF2 = new MoveToAction();
        reculF2.setPosition(xVoulu-300, yVoulu-100);
        reculF2.setDuration(1f);
        
        animF2 = new SequenceAction(attackF2,reculF2);
        
        
        useWeapon = new ImageButton(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("weapon.jpg")))));
        
        
        useWeapon.setWidth(100);
        useWeapon.setHeight(100);
        useConsommable = new ImageButton(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("consommable.jpg")))));
        
        useConsommable.setWidth(100);
        useConsommable.setHeight(100);
        
        TextButton consoLabel1= new TextButton("Pain x0",skin);
        consoLabel1.setColor(Color.CLEAR);
        consoLabel1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                useSpConso((Player)currentAttacker,"Pain");
                GameClient.sendInformation(currentAttacker.getName() + " mange 1 pain !");   
            }
        });
        
        TextButton consoLabel2= new TextButton("Vin x0",skin);
        consoLabel2.setColor(Color.CLEAR);
        consoLabel2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                useSpConso((Player)currentAttacker,"Vin");
                GameClient.sendInformation(currentAttacker.getName() + " boit une bouteille de vin !");   
            }
        });
        
        TextButton consoLabel3= new TextButton("Saucisson de Centaure x0",skin);
        consoLabel3.setColor(Color.CLEAR);
        consoLabel3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                useSpConso((Player)currentAttacker,"Saucisson de Centaure");
                GameClient.sendInformation(currentAttacker.getName() + " mange 1 saucisson !");   
            }
        });
        
        TextButton consoLabel4= new TextButton("Oeil de cylope x0",skin);
        consoLabel4.setColor(Color.CLEAR);
        consoLabel4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                useSpConso((Player)currentAttacker,"Oeil de cylope");
            }
        });
        
        TextButton consoLabel5= new TextButton("Tete de Meduse x0",skin);
        consoLabel5.setColor(Color.CLEAR);
        consoLabel5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                useSpConso((Player)currentAttacker,"Tete de Meduse");
            }
        });
        
        TextButton consoLabel6= new TextButton("Excrement de minotaure x0",skin);
        consoLabel6.setColor(Color.CLEAR);
        consoLabel6.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                useSpConso((Player)currentAttacker,"Excrement de minotaure");
            }
        });
        
        waitforattack = false;
        useWeapon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(waitforattack == false && !useWeapon.isDisabled()){

                    //redémarrage des actions
                    spriteEnnemy1.clearActions();
                    spriteEnnemy2.clearActions();
                    lostHealthF1.clearActions();
                    lostHealthF1.setPosition(-1700, 500);
                    lostHealthF2.clearActions();
                    lostHealthF2.setPosition(-1200, 500);
                    animF1.restart();
                    animF2.restart(); 
                    lossF1.restart();
                    lossF2.restart();
                    useConsommable.setDisabled(true);


                    //animation d'attaque du combattant A
                    if(currentAttacker.equals(fighterA)){
                        FightSequence(fighterA, fighterB,spriteEnnemy1,lostHealthF1,ennemyHealth2,animF1,lossF1);
                        currentAttacker = fighterB;
                    }
                    //animation d'attaque du combattant A
                    else{
                        FightSequence(fighterB, fighterA,spriteEnnemy2,lostHealthF2,ennemyHealth1,animF2,lossF2);
                        currentAttacker = fighterA;
                    }
                    //si l'ennemi est un monstre
                    if(currentAttacker.getClass().toString().equals("class com.wc.souterrain.Entity")){
                        if(currentAttacker.getHp()>0){
                            waitforattack = true;
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() { //fighterB est toujours le monstre si c'est pas un joueur
                                    FightSequence(fighterB, fighterA,spriteEnnemy2,lostHealthF2,ennemyHealth1,animF2,lossF2);
                                    currentAttacker = fighterA;

                                    Timer.schedule(new Timer.Task() { //on attend la fin de l'animation
                                        @Override
                                        public void run(){
                                            waitforattack = false;
                                        }
                                    }
                                    ,2f);
                                }
                            },2f);

                        }
                        else{
                            System.out.println("Le monstre est mort");
                        }
                    }
                }
            }
        });
        
        useConsommable.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(waitforattack==false){
                    if(useWeapon.isDisabled()){
                        useWeapon.setDisabled(false);
                        getActors().get(8).setColor(Color.CLEAR);
                        getActors().get(9).setColor(Color.CLEAR);
                        getActors().get(10).setColor(Color.CLEAR);
                        getActors().get(11).setColor(Color.CLEAR);
                        getActors().get(12).setColor(Color.CLEAR);
                        getActors().get(13).setColor(Color.CLEAR);
                        
                    }
                    else{
                        useWeapon.setDisabled(true);
                        TextButton tb = (TextButton)getActors().get(8);
                        tb.setColor(Color.WHITE);
                        tb.setText("Pain x"+countNbConso((Player)currentAttacker,"Pain").toString());
                        
                        tb = (TextButton)getActors().get(9);
                        tb.setColor(Color.WHITE);
                        tb.setText("Vin x"+countNbConso((Player)currentAttacker,"Vin").toString());
                        
                        tb = (TextButton)getActors().get(10);
                        tb.setColor(Color.WHITE);
                        tb.setText("Saucisson de Centaure x"+countNbConso((Player)currentAttacker,"Saucisson de Centaure").toString());
                        
                        tb = (TextButton)getActors().get(11);
                        tb.setColor(Color.WHITE);
                        tb.setText("Oeil de cylope x"+countNbConso((Player)currentAttacker,"Oeil de cylope").toString());
                        
                        tb = (TextButton)getActors().get(12);
                        tb.setColor(Color.WHITE);
                        tb.setText("Tete de Meduse x"+countNbConso((Player)currentAttacker,"Tete de Meduse").toString());
                        
                        tb = (TextButton)getActors().get(13);
                        tb.setColor(Color.WHITE);
                        tb.setText("Excrement de minotaure x"+countNbConso((Player)currentAttacker,"Excrement de minotaure").toString());
                    }
                }
            }
            });
        
        spriteEnnemy1 = new Image(fighterA.getSprite());
        spriteEnnemy1.setScale(4);
        
        Sprite spriteB = new Sprite(fighterB.getSprite());
        spriteB.flip(true, false);
        spriteEnnemy2 = new Image(spriteB);
        spriteEnnemy2.setScale(4);
        
        
        Integer fighterhp = fighterA.getHp();
        ennemyHealth1 = new Label(fighterhp.toString(),skin);
        fighterhp = fighterB.getHp();
        ennemyHealth2 = new Label(fighterhp.toString(),skin);
        
        lostHealthF1 = new Label("", skin, "lostHealth");
        
        MoveByAction moveLossF1 = new MoveByAction();
        moveLossF1.setAmount(0, 100);
        moveLossF1.setDuration(1f);
        
        AlphaAction dissapLossF1 = new AlphaAction();
        dissapLossF1.setAlpha(0);
        dissapLossF1.setDuration(1f);
        
        lossF1 = new ParallelAction(moveLossF1,dissapLossF1);
        
        lostHealthF2 = new Label("", skin, "lostHealth");
        
        MoveByAction moveLossF2 = new MoveByAction();
        moveLossF2.setAmount(0, 100);
        moveLossF2.setDuration(1f);
        
        AlphaAction dissapLossF2 = new AlphaAction();
        dissapLossF2.setAlpha(0);
        dissapLossF2.setDuration(1.5f);
        
        lossF2 = new ParallelAction(moveLossF2,dissapLossF2);
        
        
        //lostHealthF1.setColor(1, 0, 0, 1);
        
        
        addActor(useWeapon);
        addActor(useConsommable);
        addActor(spriteEnnemy1);
        addActor(spriteEnnemy2);
        addActor(ennemyHealth1);
        addActor(ennemyHealth2);
        addActor(lostHealthF1);
        addActor(lostHealthF2);
        addActor(consoLabel1);
        addActor(consoLabel2);
        addActor(consoLabel3);
        addActor(consoLabel4);
        addActor(consoLabel5);
        addActor(consoLabel6);
        
       
        if(fighterA.getSpeed()>fighterB.getSpeed()){
            currentAttacker = fighterA;
        }
        else{
            currentAttacker = fighterB;
        }
        
    }
    
    private void FightSequence(Entity attacker, Entity defender, //attaquant et defenseur
            Image spriteAttacker, Label lostHealthDefender, Label defenderHealth, SequenceAction animAttacker, ParallelAction lossDefender ){ //leurs animations
        int degat = attacker.attack(defender);
        GameClient.sendInformation(Integer.toString(degat));
        lostHealthDefender.setText(degat);
        Integer fighterhp = defender.getHp();
        defenderHealth.setText(fighterhp.toString());
        spriteAttacker.addAction(animAttacker);
        lostHealthDefender.setColor(1, 0, 0, 1);
        lostHealthDefender.addAction(lossDefender);
    }
    
    
    public void MoveUI(Camera camera){
        useWeapon.setPosition(camera.position.x+100, camera.position.y-300);
        useConsommable.setPosition(camera.position.x-200, camera.position.y-300);
        spriteEnnemy1.setPosition(camera.position.x+200, camera.position.y-100);
        spriteEnnemy2.setPosition(camera.position.x-300, camera.position.y-100);
        ennemyHealth1.setPosition(camera.position.x+200, camera.position.y-150);
        ennemyHealth2.setPosition(camera.position.x-300, camera.position.y-150);
        lostHealthF1.setPosition(camera.position.x+150, camera.position.y+100);
        lostHealthF2.setPosition(camera.position.x-350, camera.position.y+100);
        this.getActors().get(8).setPosition(camera.position.x-200, camera.position.y+200);
        this.getActors().get(9).setPosition(camera.position.x-200, camera.position.y+230);
        this.getActors().get(10).setPosition(camera.position.x-200, camera.position.y+260);
        this.getActors().get(11).setPosition(camera.position.x-200, camera.position.y+290);
        this.getActors().get(12).setPosition(camera.position.x-200, camera.position.y+320);
        this.getActors().get(13).setPosition(camera.position.x-200, camera.position.y+350);
    }  
    
    public Entity getFighterA(){
        return this.fighterA;
    }
    
    public Entity getFighterB(){
        return this.fighterB;
    }
    
    public Integer countNbConso(Player pl,String nameConso){
        Integer count =0;
        for(Consommable conso: pl.getInventory()){
            if (conso.getName().equals(nameConso)){
                count++;
            }
        }
        return count;
    }
    
    public void useSpConso(Player pl, String nameConso){
        for(Consommable conso: pl.getInventory()){
            if (conso.getName().equals(nameConso)){
                if(pl.equals(fighterA)){ //si le fighter actuel est le fighterA
                    pl.useInventory(conso, pl, fighterB); //attaque sur fighterB
                    Integer plhp = (Integer)pl.getHp();
                    ennemyHealth1.setText(plhp.toString());
                }
                else{
                    pl.useInventory(conso, pl, fighterA);
                    Integer plhp = (Integer)pl.getHp();
                    ennemyHealth2.setText(plhp.toString());
                }
              
                useWeapon.setDisabled(false);
                getActors().get(8).setColor(Color.CLEAR);
                getActors().get(9).setColor(Color.CLEAR);
                getActors().get(10).setColor(Color.CLEAR);
                getActors().get(11).setColor(Color.CLEAR);
                getActors().get(12).setColor(Color.CLEAR);
                getActors().get(13).setColor(Color.CLEAR);
                
                break;
            }
        }
    }
}
