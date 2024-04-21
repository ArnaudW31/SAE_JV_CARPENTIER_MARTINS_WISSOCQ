package com.wc.souterrain;

public class Weapon{
    
    
    //attributs
    private String name;
    private String description;
    private String sprite;

    // constructeur 
    public Weapon(){
        this.name = "Default";
        this.description = "this is base description, set one with setDescription()";
        this.sprite = "Default";
    }

    //méthodes

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

    public void setSprite(String sprite){
        this.sprite = sprite;
    }
    public String getSprite(){
        return this.sprite;
    }

    public void setWeapon(String name){
        this.name = name;
    }
    public String getWeapon(){
        return this.name;
    }
    
}
