package com.lence.penguinsvskillerwhales.model;

import android.graphics.Point;

import com.lence.penguinsvskillerwhales.view.UpdatePresenter;

public class Penguin implements Organism {
    private Boolean moved = false;
    private int age = 0;
    private final int lifeTime = 3;
    private UpdatePresenter updatePresenter;

    public Penguin(UpdatePresenter updatePresenter) {
        this.updatePresenter = updatePresenter;
    }

    private void addAge() {
        if (age < lifeTime) {
            age++;
        } else {
            age = 1;
        }
    }

    public void lifeCycle(Point point){
        if (!isMoved()) {
            if (getAge() == lifeTime-1) {
                updatePresenter.breeding(point, new Penguin(updatePresenter));
            }
            updatePresenter.move(point);
            addAge();
            setMoved(true);
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
