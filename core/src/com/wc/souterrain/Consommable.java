package com.wc.souterrain;

import java.util.Random;

public class Consommable{

    //attributs
    private String name;
    private String description;
    private String sprite;

    // constructeur 
    public Consommable(){
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
    public void use(Consommable conso, Player player, Entity target){
        if(conso.name.equals("Excrement de Minotaure")){
            target.setPrecision(target.getPrecision()-10);
            player.showPrecision();
        }
        else if(conso.name.equals("Pain")){
            if(player.getHp()+25 >= player.getMaxHp()){
                player.setHp(player.getMaxHp());
            }else{
                player.setHp(player.getHp()+25);
                player.showHp();
            }
            
        }
        else if(conso.name.equals("Vin")){
            if(player.getHp()+50 >= player.getMaxHp()){
                player.setHp(player.getMaxHp());
            }else{
                player.setHp(player.getHp()+50);
                player.showHp();
            }
        }
        else if(conso.name.equals("Saucisson de Centaure")){
            if(player.getHp()+75 >= player.getMaxHp()){
                player.setHp(player.getMaxHp());
            }else{
                player.setHp(player.getHp()+75);
                player.showHp();
            }
        }
        else if(conso.name.equals("Oeil de cyclope")){
            Random rand = new Random();

            int int_random = rand.nextInt(2);

            if (int_random == 0){
                player.setDmg((int) Math.round(player.getDmg()*1.15));
            }
            else{
                player.setDmg((int) Math.round(player.getDmg()*0.95));
            }
            player.showDmg();
        }
        else if(conso.name.equals("Tete de meduse")){
            target.setDef((int)Math.round(target.getDef()*0.8));
            target.showDef();
        }
    }

}

