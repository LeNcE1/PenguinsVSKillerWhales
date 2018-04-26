package com.lence.penguinsvskillerwhales.life;

import android.graphics.Point;

import com.lence.penguinsvskillerwhales.model.Orca;
import com.lence.penguinsvskillerwhales.model.Penguin;
import com.lence.penguinsvskillerwhales.utils.Constants;
import com.lence.penguinsvskillerwhales.view.KotlinField;
import com.lence.penguinsvskillerwhales.view.UpdatePresenter;

public class Eat {
    private final KotlinField mField;
    private final Point mCurrent;
    private UpdatePresenter mUpdatePresenter;
    private Orca mOrca;

    Eat(KotlinField mField, Point mCurrent, UpdatePresenter mUpdatePresenter, Orca mOrca) {
        this.mField = mField;
        this.mCurrent = mCurrent;
        this.mUpdatePresenter = mUpdatePresenter;
        this.mOrca = mOrca;
    }

    public void execute() {
        //check of all directions

        for (int direction : Constants.directions) {
            if (findPenguin(direction)) {
                eat(KotlinField.getPointByDirection(mCurrent.x, mCurrent.y,direction));
                mOrca.eat();
                mUpdatePresenter.update(mField);
            }
        }
        mOrca.addHunger();
        new Move(mUpdatePresenter, mField,mCurrent).execute();
    }


    private void eat(Point to) {
        mField.set(to.x,to.y, mField.get(mCurrent.x, mCurrent.y));
        mField.set(mCurrent.x, mCurrent.y, null);
    }

    private boolean findPenguin(int direction) {
        Point p = KotlinField.getPointByDirection(mCurrent.x, mCurrent.y, direction);
        return mField.isInField(p.x, p.y) && (mField.get(p.x, p.y) instanceof Penguin);
    }
}

