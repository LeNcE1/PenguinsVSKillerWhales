package com.lence.penguinsvskillerwhales.model;

public class Orca implements Organism {
    Boolean moved = false;
    int hunger = 0;
    int age = 0;

    public int getHunger() {
        return hunger;
    }

    public void addHunger() {
        hunger++;
    }

    public void eat() {
        hunger = 0;
    }

    public void addAge() {
        if (age < 8) {
            age++;
        } else {
            age = 1;
        }
    }

    public int getAge() {
        return age;
    }

    @Override
    public Boolean isMoved() {
        return moved;
    }

    @Override
    public void setMoved(Boolean moved) {
        this.moved = moved;
    }
}
