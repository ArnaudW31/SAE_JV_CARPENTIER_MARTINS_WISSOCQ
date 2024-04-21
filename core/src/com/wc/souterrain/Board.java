package com.wc.souterrain;

import java.util.ArrayList;

public class Board {

    // attributs
    private ArrayList<Zone> zones;
    
    // constructeur
    public Board(){
        this.zones = new ArrayList<Zone>(4);
    }

    // méthodes
    public void setZones(ArrayList<Zone> zones){
        this.zones = zones;
    }
    public ArrayList<Zone> getZones(){
        return this.zones;
    }
}
