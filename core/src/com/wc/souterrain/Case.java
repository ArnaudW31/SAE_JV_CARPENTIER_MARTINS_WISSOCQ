package com.wc.souterrain;

public class Case {
    
    // attributs
    //les cases sont composées de 9 tuiles en forme de carré sur la tilemap, les coordonnées sont celles de la case du milieu
    private int x;
    private int y;
    private String caseType;
    private Entity monster;

    // constructeur
    public Case(){
        this.x = 0;
        this.y = 0;
        this.caseType = "neutre";
        this.monster = null;
    }
    
    public Case(int nv_x, int nv_y, String nv_type){
        this.x = nv_x;
        this.y = nv_y;
        this.caseType = nv_type;
        this.monster = null;
    }

    // méthodes
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return this.y;
    }
    
    public void setType(String type){
        this.caseType = type;
    }
    public String getType(){
        return this.caseType;
    }
    
    public void setEntity(Entity nv_monster){
        this.monster = nv_monster;
    }
    
    public Entity getEntity(){
        return this.monster;
    }
    
    public boolean samePosition(Case otherCase){
        if(this.x==otherCase.getX() && this.y==otherCase.getY()){
            return true;
        }
        return false;
    }
}