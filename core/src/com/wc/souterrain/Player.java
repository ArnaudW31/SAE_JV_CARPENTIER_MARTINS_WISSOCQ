package com.wc.souterrain;

import java.util.ArrayList;

public class Player extends Entity{
    
    // attributs spécifiques à la classe Player
    private int gold;
    private ArrayList<Consommable> inventory;
    private Case currentPos;
    private String weapon;
    private boolean isAI;

    // constructeur spécifique à la classe Player
    public Player(){
        super();
        this.setMaxHp(250);
        this.setHp(this.getMaxHp());
        this.setDmg(30);
        this.setDef(20);
        this.setCrit(10);
        this.setPrecision(100);
        this.setSpeed(100);
        this.setStunChance(0);
        this.setPierceArmor(0);
        this.setInstantKillChance(0);
        this.setReflectChance(0);
        this.setStunStatus(0);
        this.gold = 100;
        this.inventory = new ArrayList<Consommable>();
        this.currentPos = new Case(0,0, "neutre");
        this.weapon = "fist";
        this.isAI = true;
    }
    
    public Player(Player play){
        super();
        this.setName(play.getName());
        this.setSprite(play.getSprite());
        this.setMaxHp(play.getMaxHp());
        this.setHp(play.getMaxHp());
        this.setDmg(play.getDmg());
        this.setDef(play.getDef());
        this.setCrit(play.getCrit());
        this.setPrecision(play.getPrecision());
        this.setSpeed(play.getSpeed());
        this.setStunChance(play.getStunChance());
        this.setPierceArmor(play.getPierceArmor());
        this.setInstantKillChance(play.getInstantKillChance());
        this.setReflectChance(play.getReflectChance());
        this.setStunStatus(play.getStunStatus());
        this.gold = play.getGold();
        this.inventory = play.getInventory();
        this.currentPos = play.getPosition();
        this.weapon = play.getWeapon();
        this.isAI = play.getAI();
    }

    // méthodes spécifiques à la classe Player
    public Case getPosition(){
        return this.currentPos;
    }

    public void setPosition(Case nv_pos){
        this.currentPos = nv_pos;
    }

    public void setGold(int gold){
        this.gold = gold;
    }
    
    public int getGold(){
        return this.gold;
    }
    
    public void setWeapon(String weapon){
        this.weapon = weapon;
    }
    
    public String getWeapon(){
        return this.weapon;
    }

    public void addToInventory(Consommable consommable){
        this.inventory.add(consommable);
    }
    public void removeFromInventory(Consommable consommable){
        this.inventory.remove(consommable);
    }
    public ArrayList<Consommable> getInventory(){
        return this.inventory;
    }
    public void useInventory(Consommable consommable, Player player, Entity target){
        consommable.use(consommable, player, target);
        player.removeFromInventory(consommable);
    }
    public void showInventory(){
        System.out.println("L'inventaire contient :");
        for (Consommable consommable : this.inventory){
            System.out.println(" -  " + consommable.getName() + " : " + consommable.getDescription());
        }
        System.out.println("\n");
    }
    public void setInventory(ArrayList<Consommable> inventory){
        this.inventory = inventory;
    }
    
    public void setAI(boolean isAI){
        this.isAI = isAI;
    }
    
    public boolean getAI(){
        return this.isAI;
    }
    
    @Override
    public boolean equals(Object o){
        boolean result = false;
        if(o.getClass() == this.getClass()){
            Player p = (Player)o;
            if(p.getName().equals(this.getName())){
                if(p.getHp()==this.getHp()){
                    if(p.getDmg()==this.getDmg()){
                        if(p.getDef()==this.getDef()){
                            if(p.getCrit()==this.getCrit()){
                                if(p.getSpeed()==this.getSpeed()){
                                    if(p.getAI()==this.getAI()){
                                        result = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return result;
    }


 
}
