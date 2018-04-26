package com.lence.penguinsvskillerwhales.model;

import android.graphics.Point;

public interface Organism {
    int getAge();
    Boolean isMoved();
    void setMoved(Boolean moved);
    void lifeCycle(Point point);

}
