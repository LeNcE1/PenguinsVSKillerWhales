package com.lence.penguinsvskillerwhales.model;

public abstract class Organism {

    Boolean moved = false;
    int age = 0;

    public int getAge() {
        return age;
    }


    public Boolean isMoved() {
        return moved;
    }

    public void setMoved(Boolean moved) {
        this.moved = moved;
    }




}
