package com.wc.souterrain;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import java.util.ArrayList;

public class Zone {
    
    //attributs
    private ArrayList<Case> cases;
    private Player player;
    private TiledMap isometricTiledMap;
    private IsometricTiledMapRenderer itmr;

    //constructeur
    public Zone(){
        this.cases = new ArrayList<Case>();
    }
    
    public Zone(Player nv_pl){
        this.cases = new ArrayList<Case>();
        this.player = nv_pl;
    }

    //méthodes
    public void setCases(ArrayList<Case> cases){
        this.cases = cases;
    }
    public ArrayList<Case> getCases(){
        return this.cases;
        // faire afficher la zone du joueur
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return this.player;
    }
    
    public void addCase(Case nv_case){
        this.cases.add(nv_case);
    }
    
    public void setTiledMap(TiledMap tm){
        this.isometricTiledMap = tm;
        this.itmr = new IsometricTiledMapRenderer(this.isometricTiledMap, 2);
    }
    
    public TiledMap getTiledMap(){
        return this.isometricTiledMap;
    }
    
    public void setRenderer(IsometricTiledMapRenderer renderer){
        this.itmr = renderer;
    }
    
    public IsometricTiledMapRenderer getRenderer(){
        return this.itmr;
    }


}
