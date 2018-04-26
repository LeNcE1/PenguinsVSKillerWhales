package com.lence.penguinsvskillerwhales.model;

import android.graphics.Point;

import com.lence.penguinsvskillerwhales.view.UpdatePresenter;

public class Orca implements Organism {
    private final int lifeTime = 8;
    private final int limitHunger = 3;

    private Boolean moved = false;
    private int hunger = 0;
    private int age = 0;
    private UpdatePresenter updatePresenter;

    public Orca(UpdatePresenter updatePresenter) {
        this.updatePresenter = updatePresenter;
    }

    private int getHunger() {
        return hunger;
    }

    public void addHunger() {
        hunger++;
    }

    public void eat() {
        hunger = 0;
    }

    private void addAge() {
        if (age < lifeTime) {
            age++;
        } else {
            age = 1;
        }
    }

    public void lifeCycle(Point point) {
        if (!isMoved()) {
            if (getHunger() < limitHunger-1) {
                if (getAge() == lifeTime-1) {
                    updatePresenter.breeding(point, new Orca(updatePresenter));
                }
                updatePresenter.eat(point, this);
                addAge();
                setMoved(true);
            } else {
                updatePresenter.death(point);

            }
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
