package com.lence.penguinsvskillerwhales;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public interface UpdatePresenter {
    void update(CopyOnWriteArrayList<Object> list);
}
