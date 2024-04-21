package com.wc.souterrain;

import com.badlogic.gdx.Gdx;
import java.util.Random;
import java.lang.Math;
import com.badlogic.gdx.graphics.Texture;

public class Entity{
    //attributs
    private String name;
    private String description;
    private int maxHp;
    private int hp;
    private int dmg;
    private int def;
    private int crit;
    private int speed;
    private int precision;
    private int StunChance;
    private int PierceArmor;
    private int InstantKillChance;
    private int ReflectChance;
    private int StunStatus;
    private String status;
    private Texture sprite;

    //constructeur
    public Entity(){
        this.name = "Default";
        this.description = "this is base description, set one with setDescription()";
        this.maxHp = 80;
        this.hp = this.maxHp;
        this.dmg = 30;
        this.def = 30;
        this.crit = 15;
        this.speed = 80;
        this.precision = 90;
        this.StunChance = 0;
        this.PierceArmor = 0;
        this.InstantKillChance = 0;
        this.ReflectChance = 0;
        this.StunStatus = 0;
        this.status = "Neutre";
        this.sprite = new Texture(Gdx.files.internal("fail_XD.png"));
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
    
    
    public void setMaxHp(int mhp){
        this.maxHp = mhp;
        this.resetHealth();
    }
    public int getMaxHp(){
        return this.maxHp;
    }
    
    public void setHp(int hp){
        this.hp = hp;
    }
    public int getHp(){
        return this.hp;
    }

    public void setDmg(int dmg){
        this.dmg = dmg;
    }
    public int getDmg(){
        return this.dmg;
    }

    public void setDef(int def){
        this.def = def;
    }
    public int getDef(){
        return this.def;
    }

    public void setCrit(int crit){
        this.crit = crit;
    }
    public int getCrit(){
        return this.crit;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getSpeed(){
        return this.speed;
    }

    public void setPrecision(int precision){
        this.precision = precision;
    }
    public int getPrecision(){
        return this.precision;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

    public void setSprite(Texture sprite){
        this.sprite = sprite;
    }
    
    public Texture getSprite(){
        return this.sprite;
    }

    public void showStat(){
        System.out.println("Nom : " + this.name);
        System.out.println("Description : " + this.description);
        System.out.println(this.name + " HP : " + this.hp);
        System.out.println(this.name + " Dmg : " + this.dmg);
        System.out.println(this.name + " Def : " + this.def);
        System.out.println(this.name + " Crit : " + this.crit);
        System.out.println(this.name + " Speed : " + this.speed);
        System.out.println(this.name + " Precision : " + this.precision);
        System.out.println(this.name + " Status : " + this.status);
        System.out.println(this.name + " Sprite : " + this.sprite);
    }

    public void showHp(){
        System.out.println(this.name + " HP : " + this.hp + "\n");
    }

    public void showDmg(){
        System.out.println(this.name + " Dmg : " + this.dmg + "\n");
    }

    public void showDef(){
        System.out.println(this.name + " Def : " + this.def + "\n");
    }

    public void showCrit(){
        System.out.println(this.name + " Crit : " + this.crit + "\n");
    }

    public void showSpeed(){
        System.out.println(this.name + " Speed : " + this.speed + "\n");
    }

    public void showPrecision(){
        System.out.println(this.name + " Precision : " + this.precision + "\n");
    }

    public void showStatus(){
        System.out.println(this.name + " Status : " + this.status + "\n");
    }

    public boolean crit_hit(){
        Random rand = new Random();

        int int_random = rand.nextInt(100);

        if (int_random < this.getCrit()){
            System.out.println("Coup critique !");
            return true;
        }
        else{
            return false;
        }
    }

    public boolean precision_landing(){
        Random rand = new Random();

        int int_random = rand.nextInt(100);

        if (int_random > this.getPrecision()){
            return true;
        }
        else{
            return false;
        }
    }

    public void setStunChance(int StunChance){
        this.StunChance = StunChance;
    }
    public int getStunChance(){
        return this.StunChance;
    }

    public void setPierceArmor(int PierceArmor){
        this.PierceArmor = PierceArmor;
    }
    public int getPierceArmor(){
        return this.PierceArmor;
    }

    public void setInstantKillChance(int InstantKillChance){
        this.InstantKillChance = InstantKillChance;
    }
    public int getInstantKillChance(){
        return this.InstantKillChance;
    }

    public void setReflectChance(int ReflectChance){
        this.ReflectChance = ReflectChance;
    }
    public int getReflectChance(){
        return this.ReflectChance;
    }

    public void setStunStatus(int StunStatus){
        this.StunStatus = StunStatus;
    }
    public int getStunStatus(){
        return this.StunStatus;
    }

    public int attack(Entity target){
        int DamageDealt = this.dmg;
        if (this.precision_landing()==true){
            DamageDealt = 0;
            System.out.println("L'attaque a échoué ! Manque de précision !");
        }
        else{
            if (this.crit_hit()==true){
                DamageDealt = DamageDealt*2;
            }
            if(target.getReflectChance()!=0){
                Random random = new Random();
                int chance = random.nextInt(100);
                if(chance<=target.getReflectChance()){
                    this.setHp(this.getHp() - DamageDealt);
                    System.out.println(this.name + " se prend un retour de dégat ! " + DamageDealt + " points de degats !");
                    return DamageDealt;
                }
            }
            if(this.getInstantKillChance()!=0){
                Random random = new Random();
                int chance = random.nextInt(100);
                if(chance<=this.getInstantKillChance()){
                    DamageDealt = 999999999;
                    target.setHp(this.getHp() - DamageDealt);
                    System.out.println(target.getName() + " se fait one shot ahah ! Deadge !");
                    return DamageDealt;
                }
            }
            if(this.getPierceArmor()!=0){
                Random random = new Random();
                int chance = random.nextInt(100);
                if(chance<=this.getPierceArmor()){
                    target.setHp(target.getHp() - DamageDealt);
                    System.out.println(this.name + " a percer son armure !" + target.getName() + " prend " + DamageDealt + " points de degats !" );
                    return DamageDealt;
                }
            }
            DamageDealt=(int) Math.round(DamageDealt*(1-(target.getDef()*0.005)));
            target.setHp(target.getHp() - DamageDealt);
            System.out.println(this.name + " inflige " + DamageDealt + " points de degats a " + target.getName() + " ! Il reste " + target.getHp() + " points de vie a " + target.getName() + " !");
        }
        return DamageDealt;
    }
    
    public void resetHealth(){
        this.hp = this.maxHp;
    }
    
    @Override
    public boolean equals(Object o){
        boolean result = false;
        if(o.getClass() == this.getClass()){
            Entity e = (Entity)o;
            if(e.getName().equals(this.getName())){
                if(e.getHp()==this.getHp()){
                    if(e.getDmg()==this.getDmg()){
                        if(e.getDef()==this.getDef()){
                            if(e.getCrit()==this.getCrit()){
                                if(e.getSpeed()==this.getSpeed()){
                                    result = true;
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