package edu.neumont.csc150.c.finalproject.model.terrains;

public class Town extends Terrain {

    public Town() {
        this.setIntroString("You find yourself at the edge of a town. According to your map, there are shops here for you to buy equipment");
        this.setName("Town");
        this.setPlaceMarker('T');
    }
}