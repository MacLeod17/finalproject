package edu.neumont.csc150.c.finalproject.model.terrains;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Terrains {
    private List<Terrain> terrains = new ArrayList<>();
    private Random gen = new Random();
    private int indexCounter;

    public Terrains() {
        terrains.add(new Forest());
        terrains.add(new Desert());
        terrains.add(new Hills());
        terrains.add(new Mountains());
        terrains.add(new Lake());
    }

    public Terrain getTerrain() {
        return this.terrains.get(this.indexCounter);
    }

    public Terrain getTerrain(int index) {
        return this.terrains.get(index);
    }

    public int getIndexCounter() {
        return this.indexCounter;
    }

    public void setIndexCounter() {
        indexCounter = gen.nextInt(terrains.size());
    }
}
