package com.lence.penguinsvskillerwhales;

import android.os.AsyncTask;

import com.lence.penguinsvskillerwhales.model.Orca;
import com.lence.penguinsvskillerwhales.model.Penguin;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Eat extends AsyncTask<Void, Void, Boolean> {
    private int mRows;
    private int mColumns;
    private UpdatePresenter mUpdatePresenter;
    private CopyOnWriteArrayList<Object> mLists;
    private int thisPosition;
    private Orca orca;
    Eat(int rows, int columns, UpdatePresenter updatePresenter, CopyOnWriteArrayList<Object> lists, int thisPosition) {
        mRows = rows;
        mColumns = columns;
        mUpdatePresenter = updatePresenter;
        mLists = lists;
        this.thisPosition = thisPosition;
        orca = ((Orca) mLists.get(thisPosition));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        //check of all directions
        if ((thisPosition % mColumns != 0) && (mLists.get(thisPosition - 1) instanceof Penguin)) {
            mLists.set(thisPosition - 1, mLists.get(thisPosition));
            mLists.set(thisPosition, false);
            return true;
        }
        if (((thisPosition + 1) % mColumns != 0) && (mLists.get(thisPosition + 1) instanceof Penguin)) {
            mLists.set(thisPosition + 1, mLists.get(thisPosition));
            mLists.set(thisPosition, false);
            return true;
        }
        if (((thisPosition - mColumns) >= 0) && (mLists.get(thisPosition - mColumns) instanceof Penguin)) {
            mLists.set(thisPosition - mColumns, mLists.get(thisPosition));
            mLists.set(thisPosition, false);
            return true;
        }
        if (((thisPosition + mColumns) < mColumns * mRows) && (mLists.get(thisPosition + mColumns) instanceof Penguin)) {
            mLists.set(thisPosition + mColumns, mLists.get(thisPosition));
            mLists.set(thisPosition, false);
            return true;
        }
        if (((thisPosition - mColumns) - 1 >= 0) && (thisPosition % mColumns != 0) && (mLists.get((thisPosition - mColumns) - 1) instanceof Penguin)) {
            mLists.set((thisPosition - mColumns) - 1, mLists.get(thisPosition));
            mLists.set(thisPosition, false);
            return true;
        }
        if (((thisPosition - mColumns) + 1 >= 0) && ((thisPosition + 1) % mColumns != 0) && (mLists.get((thisPosition - mColumns) + 1) instanceof Penguin)) {
            mLists.set((thisPosition - mColumns) + 1, mLists.get(thisPosition));
            mLists.set(thisPosition, false);
            return true;
        }
        if (((thisPosition + mColumns) - 1 < mColumns * mRows) && (thisPosition % mColumns != 0) && (mLists.get((thisPosition + mColumns) - 1) instanceof Penguin)) {
            mLists.set((thisPosition + mColumns) - 1, mLists.get(thisPosition));
            mLists.set(thisPosition, false);
            return true;
        }
        if (((thisPosition + mColumns) + 1 < mColumns * mRows) && ((thisPosition + 1) % mColumns != 0) && (mLists.get((thisPosition + mColumns) + 1) instanceof Penguin)) {
            mLists.set((thisPosition + mColumns) + 1, mLists.get(thisPosition));
            mLists.set(thisPosition, false);
            return true;
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
            orca.eat();
            mUpdatePresenter.update(mLists);
        } else {
            orca.addHunger();
            new Move(mRows, mColumns, mUpdatePresenter, mLists, thisPosition).execute();
        }
    }
}

