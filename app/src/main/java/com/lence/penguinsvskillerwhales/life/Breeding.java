package com.lence.penguinsvskillerwhales.life;

import android.graphics.Point;

import com.lence.penguinsvskillerwhales.model.Organism;
import com.lence.penguinsvskillerwhales.utils.Constants;
import com.lence.penguinsvskillerwhales.view.KotlinField;
import com.lence.penguinsvskillerwhales.view.UpdatePresenter;

import java.util.ArrayList;
import java.util.Random;

public class Breeding {
    private final KotlinField mField;
    private final Point mCurrent;
    private UpdatePresenter mUpdatePresenter;
    private Organism mOrganism;

    Breeding(KotlinField mField, Point mCurrent, UpdatePresenter mUpdatePresenter, Organism mOrganism) {
        this.mField = mField;
        this.mCurrent = mCurrent;
        this.mUpdatePresenter = mUpdatePresenter;
        this.mOrganism = mOrganism;
    }

    public void execute() {
        ArrayList<Integer> checkList = new ArrayList<>();
        //free directions list
        for (int direction : Constants.directions) {
            if (findDirection(direction)) {
                checkList.add(direction);
            }
        }
        if (checkList.size() > 0) {
            int randomNumber;
            if (checkList.size() == 1) {
                randomNumber = 0;
            } else {
                Random random = new Random();
                randomNumber = random.nextInt(checkList.size() - 1);
            }
            //creation of a new organism
            mField.set(KotlinField.getPointByDirection(mCurrent.x, mCurrent.y,checkList.get(randomNumber)), mOrganism);
        }
        mUpdatePresenter.update(mField);
    }

    private boolean findDirection(int direction) {
        Point p = KotlinField.getPointByDirection(mCurrent.x, mCurrent.y, direction);

        return mField.isInField(p.x, p.y) && (mField.get(p.x, p.y) == null);

    }
}
