package com.lence.penguinsvskillerwhales.model;

public class Penguin implements Organism {
    Boolean moved = false;
    int age = 0;

    public void addAge() {
        if (age < 3) {
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
