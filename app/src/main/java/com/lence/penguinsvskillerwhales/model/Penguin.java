package com.lence.penguinsvskillerwhales.model;

public class Penguin extends Organism {
    public void addAge() {
        if (age < 3) {
            age++;
        } else {
            age = 1;
        }
    }


}
