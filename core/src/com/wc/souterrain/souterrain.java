package com.wc.souterrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.Random;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class souterrain extends ApplicationAdapter {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;
    private Player playerFinalA;
    private Player playerFinalB;
    private Player playerFinalC;

    private SpriteBatch batch;
    private Texture backgImg;
    private Texture shopImg;
    private Sound walkSound;
    private Rectangle posPlayer;

    private Entity Skeleton;
    private Entity Zombie;
    private Entity Minotaure;
    private Entity Medusa;
    private Entity Cyclope;

    boolean seDeplace = false;
    private float dureeDeplacement = 0.5f;
    private String direction;
    private boolean inInterface = false;
    private int roundAdd = 1;

    public Case spawn1;
    public Case spawn2;
    public Case spawn3;
    public Case spawn4;
    public Case spawnF1;
    public Case spawnF2;
    public Case spawnF3;
    public Case spawnF4;

    private Zone ZoneOne;
    private Zone ZoneTwo;
    private Zone ZoneThree;
    private Zone ZoneFour;
    private Zone ZoneFive;
    private Zone ZoneSix;
    private Zone ZoneSept;
    private Zone ZoneHuit;
    public Zone currentZone;
    public Player currentPlayer;
    public IsometricTiledMapRenderer currentTilemap;
    public int round;

    public Board board;

    private MapScene UImp;
    private FightScene UIfs;
    private ShopScene UIss;
    private TitleScene UIts;
    private Stage currentStage;

    private OrthographicCamera camera;

    private float timeRemaining = 10; //1320

    private boolean lastTurn3 = false;
    private boolean lastTurn4 = false;
    private boolean prout = false;
    int premierCbtArene = 0;
    int deuxiemeCbtArene = 0;
    int troisiemeCbtArene = 0;
    int finalCbtArene = 0;

    @Override
    public  void create () {
        //gestion du jeu
        Consommable pain = new Consommable();
        pain.setName("Pain");
        player1 = new Player();
        spawn1 = new Case(32,25,"vert");
        player1.setPosition(spawn1);
        player1.addToInventory(pain);


        player2 = new Player();
        spawn2 = new Case(14, 25, "vert");
        player2.setPosition(spawn2);
        player2.addToInventory(pain);

        player3 = new Player();
        spawn3 = new Case(14, 43, "vert");
        player3.setPosition(spawn3);
        player3.addToInventory(pain);

        player4 = new Player();
        spawn4 = new Case(32, 43, "vert");
        player4.setPosition(spawn4);
        player4.addToInventory(pain);

        Skeleton = new Entity();
        Skeleton.setName("Skeleton");
        Skeleton.setSprite(new Texture(Gdx.files.internal("bones.png")));

        Zombie = new Entity();
        Zombie.setName("Zombie");
        Zombie.setSprite(new Texture(Gdx.files.internal("zombie.png")));

        Minotaure = new Entity();
        Minotaure.setName("Minotaure");
        Minotaure.setSprite(new Texture(Gdx.files.internal("mino.png")));
        Minotaure.setMaxHp(150);
        Minotaure.setDmg(40);
        Minotaure.setDef(10);
        Minotaure.setCrit(15);
        Minotaure.setPrecision(65);
        Minotaure.setSpeed(40);
        Minotaure.resetHealth();

        Medusa = new Entity();
        Medusa.setName("Medusa");
        Medusa.setSprite(new Texture(Gdx.files.internal("dusa.png")));
        Medusa.setMaxHp(100);
        Medusa.setDmg(20);
        Medusa.setDef(50);
        Medusa.setPrecision(95);
        Medusa.setSpeed(200);
        Medusa.resetHealth();

        Cyclope = new Entity();
        Cyclope.setName("Cyclope");
        Cyclope.setSprite(new Texture(Gdx.files.internal("clop.png")));
        Cyclope.setMaxHp(200);
        Cyclope.setDmg(70);
        Cyclope.setDef(0);
        Cyclope.setCrit(1);
        Cyclope.setPrecision(30);
        Cyclope.setSpeed(30);
        Cyclope.resetHealth();

        posPlayer = new Rectangle();
        posPlayer.width = 30;
        posPlayer.height = 61;
        posPlayer.x = player1.getPosition().getX() * 32 + player1.getPosition().getY() * 32;
        posPlayer.y = player1.getPosition().getY() * 16 - player1.getPosition().getX() * 16 + 40;

        //audio
        walkSound = Gdx.audio.newSound(Gdx.files.internal("sounds/footsteps.mp3"));

        //graphismes
        //création de la tilemap
        ZoneOne = new Zone(player1);
        ZoneTwo = new Zone(player2);
        ZoneThree = new Zone(player3);
        ZoneFour = new Zone(player4);
        ZoneFive = new Zone(player1);
        ZoneSix = new Zone(player3);
        ZoneSept = new Zone(playerFinalA);
        ZoneHuit = new Zone(playerFinalC);

        spawnF1 = new Case(32, 25, "vert");
        spawnF2 = new Case(26, 25, "vert");
        spawnF3 = new Case(32, 37, "vert");
        spawnF4 = new Case(26, 37, "vert");

        ZoneOne.addCase(spawn1);
        ZoneTwo.addCase(spawn2);
        ZoneThree.addCase(spawn3);
        ZoneFour.addCase(spawn4);
        ZoneFive.addCase(spawnF1);
        ZoneFive.addCase(spawnF2);
        ZoneFive.addCase(spawnF3);
        ZoneFive.addCase(spawnF4);
        ZoneSix.addCase(spawnF1);
        ZoneSix.addCase(spawnF2);
        ZoneSix.addCase(spawnF3);
        ZoneSix.addCase(spawnF4);
        ZoneSept.addCase(spawnF1);
        ZoneSept.addCase(spawnF2);
        ZoneHuit.addCase(spawnF1);

        board = new Board();
        ArrayList bz = new ArrayList<Zone>(4);
        bz.add(ZoneOne);
        bz.add(ZoneTwo);
        bz.add(ZoneThree);
        bz.add(ZoneFour);
        board.setZones(bz);

        round = 0;

        // tileMapIsometric = new TmxMapLoader().load("./tilemap/sanstitre - Copie.tmx"); // on crée une tilemap pour représenter graphiquement le plateau
        ZoneOne.setTiledMap(new TmxMapLoader().load("./tilemap/sanstitre - Copie.tmx"));
        ZoneTwo.setTiledMap(new TmxMapLoader().load("./tilemap/sanstitre - Copie.tmx"));
        ZoneThree.setTiledMap(new TmxMapLoader().load("./tilemap/sanstitre - Copie.tmx"));
        ZoneFour.setTiledMap(new TmxMapLoader().load("./tilemap/sanstitre - Copie.tmx"));
        ZoneFive.setTiledMap(new TmxMapLoader().load("./tilemap/Final.tmx"));
        ZoneSix.setTiledMap(new TmxMapLoader().load("./tilemap/Final.tmx"));
        ZoneSept.setTiledMap(new TmxMapLoader().load("./tilemap/Final2.tmx"));
        ZoneHuit.setTiledMap(new TmxMapLoader().load("./tilemap/Final3.tmx"));
        CreateTilemap();

        batch = new SpriteBatch();

        backgImg = new Texture(Gdx.files.internal("background_fight.jpg"));
        shopImg = new Texture(Gdx.files.internal("background_shop.png"));

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float zoom = (float) 1;
        camera.zoom = zoom;

        //UpdateUI(player1);
        UIss = new ShopScene(); //on doit les initialiser vide ici pour faire des vérifications sur la fin des interfaces
        UImp = new MapScene(camera);
        UIfs = new FightScene();
        UIts = new TitleScene(camera);
        Gdx.input.setInputProcessor(UIts);

        inInterface = true;
        currentStage = UIts;

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (UIts.finished) {

            // TIMER GLOBAL ---------------------------------------------
            if (timeRemaining > 0) {
                timeRemaining -= Gdx.graphics.getDeltaTime();
            } else if (timeRemaining == 0) {
                timeRemaining -= Gdx.graphics.getDeltaTime();
                // action qu'on doit définir pour tp les joueurs a la fin du tour, genre faire un (if round%4==0) avec le round
            } else if ((timeRemaining < 0) && (currentPlayer.equals(player4))) {
                lastTurn3 = true;
                timeRemaining = -1;
                player1.resetHealth();
                player2.resetHealth();
            }
            if ((lastTurn3 == true) && (round % 4 == 0)) {

                currentZone = ZoneFive;
                currentTilemap = currentZone.getRenderer();
                if (premierCbtArene == 0) {
                    if(player1.getAI()!=0){
                        player5=player1;
                        player1=player2;
                        player2=player5;
                    }
                    player1.setPosition(spawnF1);
                    currentPlayer = player1;
                    posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                    posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;
                    spawnF2.setEntity(player2);
                    spawnF3.setEntity(player3);
                    spawnF4.setEntity(player4);
                    roundAdd = 0;
                    premierCbtArene = 1;
                }
                player3.resetHealth();
                player4.resetHealth();
            }
            if ((lastTurn3 == true) && (round % 4 == 2)) {

                currentZone = ZoneSix;
                currentTilemap = currentZone.getRenderer();
                if (deuxiemeCbtArene == 0) {
                    if(player3.getAI()!=0){
                        player5=player3;
                        player3=player4;
                        player4=player5;
                    }
                    currentPlayer = player3;
                    spawnF3.setEntity(null);
                    player3.setPosition(spawnF3);
                    spawnF4.setEntity(player4);
                    spawnF1.setEntity(player1);
                    spawnF2.setEntity(player2);
                    posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                    posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;
                    deuxiemeCbtArene = 1;
                }
                lastTurn4 = true;
            }
            if ((lastTurn4 == true) && ((round % 4 == 1) || (round % 4 == 0))) {
                if(playerFinalA.getAI()!=0){
                    player5=playerFinalA;
                    playerFinalA=playerFinalB;
                    playerFinalB=player5;
                }
                currentPlayer = playerFinalA;
                if (premierCbtArene != 11) {
                    playerFinalA.resetHealth();
                    playerFinalB.resetHealth();
                    premierCbtArene = 11;
                }
                currentZone = ZoneSept;
                spawnF1.setEntity(null);
                spawnF2.setEntity(null);
                currentTilemap = currentZone.getRenderer();
                if (troisiemeCbtArene == 0) {
                    playerFinalA.setPosition(spawnF1);
                    spawnF2.setEntity(playerFinalB);
                    posPlayer.x = playerFinalA.getPosition().getX() * 32 + playerFinalA.getPosition().getY() * 32;
                    posPlayer.y = playerFinalA.getPosition().getY() * 16 - playerFinalA.getPosition().getX() * 16 + 40;
                    prout = true;
                    troisiemeCbtArene = 1;
                    lastTurn4 = false;
                }
            }

            if (!inInterface && currentPlayer.getAI()==0) {
                //System.out.println("pas ia");
                if (Gdx.input.isKeyPressed(Keys.RIGHT) && !seDeplace) {
                    Case nextPos = new Case(currentPlayer.getPosition().getX() + 6, currentPlayer.getPosition().getY(), ""); //on calcule la case d'arrivée
                    for (Case c : currentZone.getCases()) { //parmis toutes les cases de la zone

                        if (c.samePosition(nextPos)) { //si il y en a une à la position de la case d'arrivée calculée alors il existe une case et le joueur peut s'y déplacer
                            seDeplace = true;
                            direction = "droite";

                            //déplacement du sprite du joueur au coordonnées de la case où il se trouve
                            //formules pour passer des coordonnées sur la tilemap aux coordonnées dans le jeu, elles sont bizarres mais c'est isometrique donc normal
                            posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32; //nv_x = co_x_tilemap*32 + co_y_tilemap*32
                            posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;//nv_y =co_y_tilemap*16 - co_x_tilemap*16
                            currentPlayer.setPosition(nextPos);
                            walkSound.play(9);
                            Timer.schedule(new Timer.Task() { //on crée un time qui s'exécutera 0.5 secondes après
                                @Override
                                public void run() { //le timer fera ça à la fin du décompte
                                    deplacement();
                                }
                            }, dureeDeplacement);
                        }
                    }
                }

                if (Gdx.input.isKeyPressed(Keys.LEFT) && !seDeplace) {
                    Case nextPos = new Case(currentPlayer.getPosition().getX() - 6, currentPlayer.getPosition().getY(), "");
                    for (Case c : currentZone.getCases()) {

                        if (c.samePosition(nextPos)) {
                            seDeplace = true;
                            direction = "gauche";
                            posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                            posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;
                            currentPlayer.setPosition(nextPos);
                            walkSound.play(9);
                            Timer.schedule(new Timer.Task() { //on crée un timer qui s'exécutera 0.5 secondes après
                                @Override
                                public void run() { //le timer fera ça à la fin du décompte
                                    deplacement();
                                }
                            }, dureeDeplacement);
                        }
                    }
                }

                if (Gdx.input.isKeyPressed(Keys.UP) && !seDeplace) {
                    Case nextPos = new Case(currentPlayer.getPosition().getX(), currentPlayer.getPosition().getY() + 6, "");
                    for (Case c : currentZone.getCases()) {

                        if (c.samePosition(nextPos)) {
                            seDeplace = true;
                            direction = "haut";
                            posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                            posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;
                            currentPlayer.setPosition(nextPos);
                            walkSound.play(9);
                            Timer.schedule(new Timer.Task() { //on crée un timer qui s'exécutera 0.5 secondes après
                                @Override
                                public void run() { //le timer fera ça à la fin du décompte
                                    deplacement();
                                }
                            }, dureeDeplacement);
                        }
                    }
                }

                if (Gdx.input.isKeyPressed(Keys.DOWN) && !seDeplace) {
                    Case nextPos = new Case(currentPlayer.getPosition().getX(), currentPlayer.getPosition().getY() - 6, "");
                    for (Case c : currentZone.getCases()) {

                        if (c.samePosition(nextPos)) {
                            seDeplace = true;
                            direction = "bas";
                            posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                            posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;
                            currentPlayer.setPosition(nextPos);
                            walkSound.play(9);
                            Timer.schedule(new Timer.Task() { //on crée un timer qui s'exécutera 0.5 secondes après
                                @Override
                                public void run() { //le timer fera ça à la fin du décompte
                                    deplacement();
                                }
                            }, dureeDeplacement);
                        }
                    }
                }
                    if(Gdx.input.isKeyPressed(Keys.DOWN) && !seDeplace){
                        Case nextPos = new Case(currentPlayer.getPosition().getX(),currentPlayer.getPosition().getY()-6, "");
                        for(Case c : currentZone.getCases()){

                            if(c.samePosition(nextPos)){
                                seDeplace = true;
                                direction = "bas";
                                posPlayer.x = currentPlayer.getPosition().getX()*32+currentPlayer.getPosition().getY()*32;
                                posPlayer.y = currentPlayer.getPosition().getY()*16-currentPlayer.getPosition().getX()*16+40;
                                currentPlayer.setPosition(nextPos);
                                walkSound.play(9);
                                Timer.schedule(new Timer.Task() { //on crée un timer qui s'exécutera 0.5 secondes après
                                    @Override
                                    public void run() { //le timer fera ça à la fin du décompte
                                        deplacement();
                                    }
                                },dureeDeplacement);
                            }
                        }
                    }   
                
                //posPlayer.setPosition(UImp.attributeList.getChild(0).getX(), UImp.attributeList.getChild(0).getY());
                //System.out.println(posPlayer.x+"    "+posPlayer.y);
                camera.position.set(posPlayer.x, posPlayer.y, 0);
                UImp.MoveUI(camera);
            } else if ((currentPlayer.getAI() == 1 || currentPlayer.getAI() == 2 || currentPlayer.getAI() == 3) && !seDeplace && !inInterface) {
                if (!seDeplace) {
                    Random random = new Random();
                    int chance = random.nextInt(4);
                    Case nextPos;
                    switch (chance) {
                        case 0:
                            nextPos = new Case(currentPlayer.getPosition().getX(), currentPlayer.getPosition().getY() - 6, "");
                            for (Case c : currentZone.getCases()) {

                                if (c.samePosition(nextPos)) {
                                    seDeplace = true;
                                    direction = "bas";
                                    posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                                    posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;
                                    currentPlayer.setPosition(new Case(currentPlayer.getPosition().getX(), currentPlayer.getPosition().getY() - 6, ""));
                                    walkSound.play(9);
                                    Timer.schedule(new Timer.Task() { //on crée un timer qui s'exécutera 0.5 secondes après
                                        @Override
                                        public void run() { //le timer fera ça à la fin du décompte
                                            deplacement();
                                        }
                                    }, dureeDeplacement);
                                }
                            }
                            break;
                        case 1:
                            nextPos = new Case(currentPlayer.getPosition().getX(), currentPlayer.getPosition().getY() + 6, "");
                            for (Case c : currentZone.getCases()) {

                                if (c.samePosition(nextPos)) {
                                    seDeplace = true;
                                    direction = "haut";
                                    posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                                    posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;
                                    currentPlayer.setPosition(nextPos);
                                    walkSound.play(9);
                                    Timer.schedule(new Timer.Task() { //on crée un timer qui s'exécutera 0.5 secondes après
                                        @Override
                                        public void run() { //le timer fera ça à la fin du décompte
                                            deplacement();
                                        }
                                    }, dureeDeplacement);
                                }
                            }
                            break;
                        case 2:
                            nextPos = new Case(currentPlayer.getPosition().getX() - 6, currentPlayer.getPosition().getY(), "");
                            for (Case c : currentZone.getCases()) {

                                if (c.samePosition(nextPos)) {
                                    seDeplace = true;
                                    direction = "gauche";
                                    posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                                    posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;
                                    currentPlayer.setPosition(nextPos);
                                    walkSound.play(9);
                                    Timer.schedule(new Timer.Task() { //on crée un timer qui s'exécutera 0.5 secondes après
                                        @Override
                                        public void run() { //le timer fera ça à la fin du décompte
                                            deplacement();
                                        }
                                    }, dureeDeplacement);
                                }
                            }
                            break;
                        case 3:
                            nextPos = new Case(currentPlayer.getPosition().getX() + 6, currentPlayer.getPosition().getY(), "");
                            for (Case c : currentZone.getCases()) {

                                if (c.samePosition(nextPos)) {
                                    seDeplace = true;
                                    direction = "droite";
                                    posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32; //nv_x = co_x_tilemap*32 + co_y_tilemap*32
                                    posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;//nv_y =co_y_tilemap*16 - co_x_tilemap*16
                                    System.out.println("np " + nextPos.getX() + " " + nextPos.getY());
                                    System.out.println("cp " + currentPlayer.getPosition().getX() + " " + currentPlayer.getPosition().getY());
                                    currentPlayer.setPosition(nextPos);
                                    walkSound.play(9);
                                    Timer.schedule(new Timer.Task() { //on crée un time qui s'exécutera 0.5 secondes après
                                        @Override
                                        public void run() { //le timer fera ça à la fin du décompte
                                            deplacement();
                                        }
                                    }, dureeDeplacement);
                                }
                            }
                            break;

                    }
                }
                camera.position.set(posPlayer.x, posPlayer.y, 0);
                UImp.MoveUI(camera);

            } else if(inInterface) { //si on est dans une interface
                if (UIfs.isFighting == true) { //si on est en train de faire un combat
                    if(UIfs.getFighterB().getHp()<=0){ //fin du combat (on peut changer)
                        if(GameClient.SERVER_ADDRESS != null)
                            GameClient.sendInformation(UIfs.getFighterA().getName() + " remporte le combat contre : " + UIfs.getFighterB().getName() + ". Il termine son tour.");   
                        if(UIfs.getFighterB().getClass().toString().equals("class com.wc.souterrain.Player")){
                            if(playerFinalA==null){
                                Player p = (Player) UIfs.getFighterA();
                                playerFinalA = new Player(p);
                                System.out.println(playerFinalA.getName() + " est le playerFinalA. PlayerFinalA est défini.");
                                roundAdd = 0;
                                round = 2;
                                playerFinalA.resetHealth();
                            } else if (playerFinalB == null) {
                                Player p = (Player) UIfs.getFighterA();
                                playerFinalB = new Player(p);
                                System.out.println(playerFinalB.getName() + " est le playerFinalB. PlayerFinalB est défini.");
                                roundAdd = 0;
                                if (playerFinalA.getName().equals(player1.getName())) {
                                    round = 0;
                                } else if (playerFinalA.getName().equals(player2.getName())) {
                                    round = 1;
                                }
                                playerFinalA.resetHealth();
                                playerFinalB.resetHealth();
                            } else if (playerFinalC == null) {
                                Player p = (Player) UIfs.getFighterA();
                                playerFinalC = new Player(p);
                                System.out.println(playerFinalC.getName() + " est le playerFinalC. PlayerFinalC est défini.");
                                roundAdd = 0;
                                if (player1.equals(playerFinalC)) {
                                    round = 0;
                                } else if (player2.equals(playerFinalC)) {
                                    round = 1;
                                } else if (player3.equals(playerFinalC)) {
                                    round = 2;
                                } else if (player4.equals(playerFinalC)) {
                                    round = 3;
                                }
                                playerFinalA.resetHealth();
                                playerFinalB.resetHealth();
                                playerFinalC.resetHealth();
                            }
                        } else {
                            Random random = new Random();
                            int statToIncrease = random.nextInt(3);
                            switch (statToIncrease) {
                                case 0:
                                    currentPlayer.setDmg(UIfs.getFighterA().getDmg() + 5);
                                    break;
                                case 1:
                                    currentPlayer.setMaxHp(UIfs.getFighterA().getMaxHp() + 10);
                                    break;
                                case 2:
                                    currentPlayer.setDef(UIfs.getFighterA().getDef() + 10);
                                    break;
                            }
                        }
                        UIfs.getFighterB().resetHealth();
                        UIfs.isFighting = false;
                        inInterface = false;
                        round += roundAdd;

                        if (UIfs.getFighterB().getName().equals("Minotaure")) {
                            Consommable excm = new Consommable();
                            excm.setName("Excrement de Minotaure");
                            currentPlayer.addToInventory(excm);
                        }

                        if (UIfs.getFighterB().getName().equals("Medusa")) {
                            Consommable tdm = new Consommable();
                            tdm.setName("Tete de meduse");
                            currentPlayer.addToInventory(tdm);
                        }

                        if (UIfs.getFighterB().getName().equals("Cyclope")) {
                            Consommable odc = new Consommable();
                            odc.setName("Oeil de cyclope");
                            currentPlayer.addToInventory(odc);
                        }

                        currentPlayer.setGold(currentPlayer.getGold() + 30);

                        currentZone = board.getZones().get(round % 4);
                        currentPlayer = currentZone.getPlayer();
                        currentTilemap = currentZone.getRenderer();

                        posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                        posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;

                        if ((playerFinalC != null)) {
                            currentPlayer = playerFinalC;
                            currentZone = ZoneHuit;
                            currentTilemap = currentZone.getRenderer();
                            playerFinalC.setPosition(spawnF1);
                            posPlayer.x = playerFinalC.getPosition().getX() * 32 + playerFinalC.getPosition().getY() * 32;
                            posPlayer.y = playerFinalC.getPosition().getY() * 16 - playerFinalC.getPosition().getX() * 16 + 40;
                        }

                        currentStage = UImp;

                        UpdateUI(currentPlayer);
                    }

                    if (UIfs.getFighterA().getHp() <= 0) { //fin du combat (on peut changer)
                        if (UIfs.getFighterB().getClass().toString().equals("class com.wc.souterrain.Player")) {
                            if (playerFinalA == null) {
                                Player p = (Player) UIfs.getFighterB();
                                playerFinalA = new Player(p);
                                System.out.println(playerFinalA.getName() + " est le playerFinalA. PlayerFinalA est défini.");
                                roundAdd = 0;
                                round = 2;
                                playerFinalA.resetHealth();
                            } else if (playerFinalB == null) {
                                Player p = (Player) UIfs.getFighterB();
                                playerFinalB = new Player(p);
                                System.out.println(playerFinalB.getName() + " est le playerFinalB. PlayerFinalB est défini.");
                                roundAdd = 0;
                                if (playerFinalA.getName().equals(player1.getName())) {
                                    round = 0;
                                } else if (playerFinalA.getName().equals(player2.getName())) {
                                    round = 1;
                                }
                                playerFinalB.resetHealth();
                                playerFinalA.resetHealth();
                            } else if (playerFinalC == null) {
                                Player p = (Player) UIfs.getFighterB();
                                playerFinalC = new Player(p);
                                System.out.println(playerFinalC.getName() + " est le playerFinalC. PlayerFinalC est défini.");
                                roundAdd = 0;
                                if (player1.equals(playerFinalC)) {
                                    round = 0;
                                } else if (player2.equals(playerFinalC)) {
                                    round = 1;
                                } else if (player3.equals(playerFinalC)) {
                                    round = 2;
                                } else if (player4.equals(playerFinalC)) {
                                    round = 3;
                                }
                                playerFinalA.resetHealth();
                                playerFinalB.resetHealth();
                                playerFinalC.resetHealth();
                            }
                        } else {
                            currentPlayer.setGold(currentPlayer.getGold() / 2);
                        }
                        UIfs.getFighterA().resetHealth();
                        UIfs.isFighting = false;
                        inInterface = false;
                        round += roundAdd;

//                        Random random = new Random();
//                        int statToIncrease = random.nextInt(3);
//                        switch (statToIncrease) {
//                            case 0:
//                                UIfs.getFighterB().setDmg(UIfs.getFighterB().getDmg() + 5); 
//                                break;
//                            case 1:
//                                UIfs.getFighterB().setHp(UIfs.getFighterB().getHp() + 10); 
//                                break;
//                            case 2:
//                                UIfs.getFighterB().setDef(UIfs.getFighterB().getDef() + 10);
//                                break;
//                        }
                        currentZone = board.getZones().get(round % 4);
                        currentPlayer = currentZone.getPlayer();
                        currentTilemap = currentZone.getRenderer();

                        posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                        posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;

                        if ((playerFinalC != null)) {
                            currentPlayer = playerFinalC;
                            currentZone = ZoneHuit;
                            currentTilemap = currentZone.getRenderer();
                            playerFinalC.setPosition(spawnF1);
                            posPlayer.x = playerFinalC.getPosition().getX() * 32 + playerFinalC.getPosition().getY() * 32;
                            posPlayer.y = playerFinalC.getPosition().getY() * 16 - playerFinalC.getPosition().getX() * 16 + 40;
                        }

                        currentStage = UImp;

                        UpdateUI(currentPlayer);
                    }
                } else if (UIss.isShopping == false) { //si on est dans une interface et qu'on est pas en train de combattre, alors on fait du shopping girlllllllllllllllll
                    inInterface = false;

                    currentZone = board.getZones().get(round % 4);
                    currentPlayer = currentZone.getPlayer();
                    currentTilemap = currentZone.getRenderer();

                    posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
                    posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;

                    currentStage = UImp;

                    UpdateUI(currentPlayer);
                }
            }

            if (seDeplace) { //si le perso doit se deplacer
                switch (direction) {
                    case "droite":
                        posPlayer.x += 384 * Gdx.graphics.getDeltaTime();
                        posPlayer.y -= 192 * Gdx.graphics.getDeltaTime();
                        break;
                    case "gauche":
                        posPlayer.x -= 384 * Gdx.graphics.getDeltaTime();
                        posPlayer.y += 192 * Gdx.graphics.getDeltaTime();
                        break;
                    case "haut":
                        posPlayer.x += 384 * Gdx.graphics.getDeltaTime();
                        posPlayer.y += 192 * Gdx.graphics.getDeltaTime();
                        break;
                    case "bas":
                        posPlayer.x -= 384 * Gdx.graphics.getDeltaTime();
                        posPlayer.y -= 192 * Gdx.graphics.getDeltaTime();
                        break;
                }
            }
        }

        batch.setProjectionMatrix(camera.combined); //honnetement jsp ce que ca fait
        camera.update();

        if (UIts.finished) {
            currentTilemap.setView(camera);
        } else if (UIts.init) {
            UIts.finished = true;
            InitCharacters();
            inInterface = false;

            currentStage = UImp;
            UIts.dispose();
        }

        draw();
    }

    public void draw() {

        if (UIts.finished) {
            batch.begin();
            currentTilemap.render();
            batch.draw(currentPlayer.getSprite(), posPlayer.x + 6, posPlayer.y + 5, 75, 140);
            batch.draw(backgImg, -2000, 0, 1000, 800);
            batch.draw(shopImg, -2000, 2000, 1000, 800);
            for (Case c : currentZone.getCases()) {
                if (c.getEntity() != null) {
                    batch.draw(c.getEntity().getSprite(), c.getX() * 32 + c.getY() * 32, c.getY() * 16 - c.getX() * 16 + 40, 80, 150);
                }
            }
            batch.end();
            timeRemaining -= Gdx.graphics.getDeltaTime();
            if (timeRemaining >= 0) {
                UImp.updateTimeRemaining(timeRemaining);
            } else if (timeRemaining < 0) {
                UImp.timeDisappear();
            }
        }

        currentStage.act(Gdx.graphics.getDeltaTime());
        currentStage.draw();

    }

    @Override
    public void dispose() {
        batch.dispose();
        player1.getSprite().dispose();
        Skeleton.getSprite().dispose();
        backgImg.dispose();
        UImp.dispose();
        UIfs.dispose();
        UIss.dispose();

    }

    private void CreateTilemap() {
        //LA COORDONNEE 0 0 SUR LA TILEMAP EST TOUT A GAUCHE ET Y FAIT ALLER EN HAUT A DROITE ET X EN BAS A DROITE

        TiledMapTileLayer layer = (TiledMapTileLayer) ZoneOne.getTiledMap().getLayers().get("lay1");
        Cell greyCell = layer.getCell(0, 1);
        Cell greenCell = layer.getCell(0, 0);
        Cell redCell = layer.getCell(1, 0);
        Cell goldCell = layer.getCell(1, 1);
        Cell replacing = greyCell;
        for (Zone z : board.getZones()) {

            CreateZones(z);

            for (Case c : z.getCases()) {
                //à changer pour un switch
                if (c.getType().equals("neutre")) { //selon le type de la case, ce sera pas la même tuile à utiliser
                    replacing = greyCell;
                }
                if (c.getType().equals("vert")) {
                    replacing = greenCell;
                }
                if (c.getType().equals("artefact")) {
                    replacing = redCell;
                }
                if (c.getType().equals("shop")) {
                    replacing = goldCell;
                }
                if (c.getType().equals("mboss")) {
                    replacing = redCell;
                }
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        layer = (TiledMapTileLayer) z.getTiledMap().getLayers().get("lay1");
                        layer.setCell(c.getX() + i, c.getY() + j, replacing);
                    }
                }
            }
        }
    }

    private void CreateZones(Zone toFill) {
        boolean alreadyExists;
        ArrayList<String> ObligatoryCaseType = new ArrayList<>(16);
        ObligatoryCaseType.add("artefact");
        ObligatoryCaseType.add("shop");
        ObligatoryCaseType.add("mboss");
        ObligatoryCaseType.add("mboss");
        for (int q = 4; q < 9; q++) {
            ObligatoryCaseType.add("neutre");
        }

        for (int q = 9; q < 16; q++) {
            ObligatoryCaseType.add("vert");
        }

        for (int case_x = 14; case_x <= 32; case_x = case_x + 6) {

            for (int case_y = 25; case_y <= 43; case_y = case_y + 6) {

                Case possibleCase = new Case(case_x, case_y, "");
                alreadyExists = false;

                for (Case c : toFill.getCases()) {
                    if (c.samePosition(possibleCase)) {
                        alreadyExists = true;
                    }
                }
                if (alreadyExists == false) {
                    int randomType = ThreadLocalRandom.current().nextInt(0, ObligatoryCaseType.size());
                    possibleCase.setType(ObligatoryCaseType.get(randomType));

                    if ((ObligatoryCaseType.get(randomType)).equals("vert")) {
                        int randomMonster = ThreadLocalRandom.current().nextInt(0, 2);

                        switch (randomMonster) {
                            case 0:
                                possibleCase.setEntity(Skeleton);
                                break;
                            case 1:
                                possibleCase.setEntity(Zombie);
                                break;
                        }

                    }

                    if ((ObligatoryCaseType.get(randomType)).equals("mboss")) {
                        int randomMonster = ThreadLocalRandom.current().nextInt(0, 3);

                        switch (randomMonster) {
                            case 0:
                                possibleCase.setEntity(Minotaure);
                                break;
                            case 1:
                                possibleCase.setEntity(Medusa);
                                break;
                            case 2:
                                possibleCase.setEntity(Cyclope);
                        }

                    }
                    ObligatoryCaseType.remove(randomType);

                    toFill.addCase(possibleCase);
                }
            }
        }
    }

    public void InitCharacters(){
        player1.setName(UIts.nameList.get(0));
        player1.setSprite(new Texture(Gdx.files.internal(UIts.spriteList.get(0))));
        player1.setAI(UIts.addAI.get(0));
        System.out.println(UIts.addAI.get(0));

        player2.setName(UIts.nameList.get(1));
        player2.setSprite(new Texture(Gdx.files.internal(UIts.spriteList.get(1))));
        player2.setAI(UIts.addAI.get(1));
        System.out.println(UIts.addAI.get(1));

        player3.setName(UIts.nameList.get(2));
        player3.setSprite(new Texture(Gdx.files.internal(UIts.spriteList.get(2))));
        player3.setAI(UIts.addAI.get(2));
        System.out.println(UIts.addAI.get(2));

        player4.setName(UIts.nameList.get(3));
        player4.setSprite(new Texture(Gdx.files.internal(UIts.spriteList.get(3))));
        player4.setAI(UIts.addAI.get(3));
        System.out.println(UIts.addAI.get(3));

        currentZone = board.getZones().get(round % 4);
        currentPlayer = currentZone.getPlayer();
        currentTilemap = currentZone.getRenderer();

        posPlayer.x = currentPlayer.getPosition().getX() * 32 + currentPlayer.getPosition().getY() * 32;
        posPlayer.y = currentPlayer.getPosition().getY() * 16 - currentPlayer.getPosition().getX() * 16 + 40;

        currentStage = UImp;

        UpdateUI(currentPlayer);

        Integer temp = Integer.parseInt(UIts.selectTime.getText());
        timeRemaining = temp * 2;
    }

    private void UpdateUI(Player pl) {
        //Ici il faudra mettre toute les stats du currentPlayer dans les labels correspondants
        UImp.setPlayerName(pl.getName());
        UImp.updateStats(pl.getDmg(), pl.getDef(), pl.getCrit(), pl.getSpeed(), pl.getPrecision(), pl.getGold(), pl.getWeapon());
    }

    private void SetupFight(Entity ennemy) {
        inInterface = true;

        camera.position.set(-1500, 400, 0);
        UIfs = new FightScene(camera, currentPlayer, ennemy);
        camera.position.set(-1500, 400, 0);
        currentStage = UIfs;
        UIfs.MoveUI(camera);
        seDeplace = false;

        Gdx.input.setInputProcessor(UIfs);
        /*FitViewport viewport = new FitViewport(width, height); // Remplacez width et height par les dimensions souhaitées.
        Stage stage = new Stage(viewport);
        CombatUI combatUi = new CombatUI(stage.getViewport()); */
    }

    private void SetupShop() {
        inInterface = true;

        camera.position.set(-1500, 2400, 0);
        UIss = new ShopScene(camera, currentPlayer);
        camera.position.set(-1500, 2400, 0);
        currentStage = UIss;
        UIss.MoveUI(camera);

        Gdx.input.setInputProcessor(UIss);
    }

    private void deplacement() {
        seDeplace = false;
        for (Case c : currentZone.getCases()) {

            if (currentPlayer.getPosition().samePosition(c)) {

                if (c.getEntity() != null) {
                    SetupFight(c.getEntity());
                    c.setEntity(null);
                }

                if (c.getType().equals("shop")) {
                    SetupShop();
                }

                if (c.getType().equals("artefact")) {
                    UImp.obtainWeapon(currentPlayer);
                }
            }
        }
    }
}
