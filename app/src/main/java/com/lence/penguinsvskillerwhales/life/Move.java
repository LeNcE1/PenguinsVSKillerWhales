package com.lence.penguinsvskillerwhales.life;

import android.graphics.Point;

import com.lence.penguinsvskillerwhales.utils.Constants;
import com.lence.penguinsvskillerwhales.view.KotlinField;
import com.lence.penguinsvskillerwhales.view.UpdatePresenter;

import java.util.Random;

public class Move {
    private UpdatePresenter mUpdatePresenter;
    private final KotlinField mField;
    private final Point mCurrent;

    Move(UpdatePresenter updatePresenter, KotlinField field, Point mCurrent) {
        mUpdatePresenter = updatePresenter;
        mField = field;
        this.mCurrent = mCurrent;
    }


    public void execute() {
        Random random = new Random();
        int randomInt = random.nextInt(Constants.count_choice);

        if (findDirection(randomInt)) {
            move(KotlinField.getPointByDirection(mCurrent.x,mCurrent.y,randomInt));
        }

        mUpdatePresenter.update(mField);
    }


    private void move(Point to) {
        mField.set(to.x, to.y, mField.get(mCurrent.x, mCurrent.y));
        mField.set(mCurrent.x, mCurrent.y, null);
        //Log.e("move", "move " + to + " to right down");
    }

    private boolean findDirection(int direction) {
        Point p = KotlinField.getPointByDirection(mCurrent.x,mCurrent.y, direction);
        //Log.e("point",p +" "+ mField.isInField(p.x, p.y));
        return (mField.isInField(p.x, p.y) && (mField.get(p.x, p.y) == null));

    }



}
