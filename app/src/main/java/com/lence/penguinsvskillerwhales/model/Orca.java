package com.lence.penguinsvskillerwhales.model;

public class Orca extends Organism {
    int hunger=0;

    public int getHunger() {
        return hunger;
    }

    public void addHunger() {
        hunger++;
    }

    public void eat(){
        hunger=0;
    }

    public void addAge() {
        if (age < 8) {
            age++;
        } else {
            age = 1;
        }
    }

}
