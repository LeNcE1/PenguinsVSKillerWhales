package com.lence.penguinsvskillerwhales;

import android.os.AsyncTask;

import com.lence.penguinsvskillerwhales.model.Organism;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Breeding extends AsyncTask<Void,Void,Void> {
    private int mRows;
    private int mColumns;
    private UpdatePresenter mUpdatePresenter;
    private CopyOnWriteArrayList<Object> mLists;
    private int thisPosition;
    Organism mOrganism;

    public Breeding(int rows, int columns, UpdatePresenter updatePresenter, CopyOnWriteArrayList<Object> lists, int thisPosition, Organism organism) {
        mRows = rows;
        mColumns = columns;
        mUpdatePresenter = updatePresenter;
        mLists = lists;
        this.thisPosition = thisPosition;
        mOrganism = organism;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ArrayList<Integer> checkList = new ArrayList<>();
        //free directions list
        if ((thisPosition % mColumns != 0) && (mLists.get(thisPosition - 1) instanceof Boolean)) {
            checkList.add(0);
        }
        if (((thisPosition + 1) % mColumns != 0) && (mLists.get(thisPosition + 1) instanceof Boolean)) {
            checkList.add(1);
        }
        if (((thisPosition - mColumns) >= 0) && (mLists.get(thisPosition - mColumns) instanceof Boolean)) {
            checkList.add(2);
        }
        if (((thisPosition + mColumns) < mColumns * mRows) && (mLists.get(thisPosition + mColumns) instanceof Boolean)) {
            checkList.add(3);
        }
        if (((thisPosition - mColumns) - 1 >= 0) && (thisPosition % mColumns != 0) && (mLists.get((thisPosition - mColumns) - 1) instanceof Boolean)) {
            checkList.add(4);
        }
        if (((thisPosition - mColumns) + 1 >= 0) && ((thisPosition + 1) % mColumns != 0) && (mLists.get((thisPosition - mColumns) + 1) instanceof Boolean)) {
            checkList.add(5);
        }
        if (((thisPosition + mColumns) - 1 < mColumns * mRows) && (thisPosition % mColumns != 0) && (mLists.get((thisPosition + mColumns) - 1) instanceof Boolean)) {
            checkList.add(6);
        }
        if (((thisPosition + mColumns) + 1 < mColumns * mRows) && ((thisPosition + 1) % mColumns != 0) && (mLists.get((thisPosition + mColumns) + 1) instanceof Boolean)) {
            checkList.add(7);
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
            switch (checkList.get(randomNumber)) {
                case 0: {
                    mLists.set(thisPosition - 1, mOrganism);
                    break;
                }
                case 1: {
                    mLists.set(thisPosition + 1, mOrganism);
                    break;
                }
                case 2: {
                    mLists.set(thisPosition - mColumns, mOrganism);
                    break;
                }
                case 3: {
                    mLists.set(thisPosition + mColumns, mOrganism);
                    break;
                }
                case 4: {
                    mLists.set((thisPosition - mColumns) - 1, mOrganism);
                    break;
                }
                case 5: {
                    mLists.set((thisPosition - mColumns) + 1, mOrganism);
                    break;
                }
                case 6: {
                    mLists.set((thisPosition + mColumns) - 1, mOrganism);
                    break;
                }
                case 7: {
                    mLists.set((thisPosition + mColumns) + 1, mOrganism);
                    break;
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mUpdatePresenter.update(mLists);
    }
}
