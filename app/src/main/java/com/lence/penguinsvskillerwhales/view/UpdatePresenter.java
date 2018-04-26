package com.lence.penguinsvskillerwhales.view;

import android.graphics.Point;

import com.lence.penguinsvskillerwhales.model.Orca;
import com.lence.penguinsvskillerwhales.model.Organism;

public interface UpdatePresenter {
    void update(KotlinField list);
    void move(Point thisPoint);
    void breeding(Point thisPoint, Organism organism);
    void eat(Point thisPoint, Orca orca);
    void death(Point thisPoint);
}
