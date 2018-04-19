package com.lence.penguinsvskillerwhales;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Move extends AsyncTask<Void, Void, Void> {
    private int mRows;
    private int mColumns;
    private UpdatePresenter mUpdatePresenter;
    private CopyOnWriteArrayList<Object> mLists;
    private int thisPosition;

    Move(int rows, int columns, UpdatePresenter updatePresenter, CopyOnWriteArrayList<Object> lists, int thisPosition) {
        mRows = rows;
        mColumns = columns;
        mUpdatePresenter = updatePresenter;
        mLists = lists;
        this.thisPosition = thisPosition;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Random random = new Random();
        switch (random.nextInt(7)) {
            case 0: {
                //left
                if ((thisPosition % mColumns != 0) && (mLists.get(thisPosition - 1) instanceof Boolean)) {
                    mLists.set(thisPosition - 1, mLists.get(thisPosition));
                    mLists.set(thisPosition, false);
                    Log.e("move", "move " + thisPosition + " to left");
                }
                break;
            }

            case 1: {
                //right
                if (((thisPosition + 1) % mColumns != 0) && (mLists.get(thisPosition + 1) instanceof Boolean)) {
                    mLists.set(thisPosition + 1, mLists.get(thisPosition));
                    mLists.set(thisPosition, false);
                    Log.e("move", "move " + thisPosition + " to right");
                }
                break;
            }
            case 2: {
                //up
                //Log.e("up", "position "+thisPosition+" "+(thisPosition-mColumns)+" "+((thisPosition-mColumns)>0?"true":"false"));
                if (((thisPosition - mColumns) >= 0) && (mLists.get(thisPosition - mColumns) instanceof Boolean)) {
                    mLists.set(thisPosition - mColumns, mLists.get(thisPosition));
                    mLists.set(thisPosition, false);
                    Log.e("move", "move " + thisPosition + " to up");
                }

                break;
            }
            case 3: {
                //down
                if (((thisPosition + mColumns) < mColumns * mRows) && (mLists.get(thisPosition + mColumns) instanceof Boolean)) {
                    mLists.set(thisPosition + mColumns, mLists.get(thisPosition));
                    mLists.set(thisPosition, false);
                    Log.e("move", "move " + thisPosition + " to down");
                }
                break;
            }
            case 4: {
                //left up
                if (((thisPosition - mColumns) - 1 >= 0) && (thisPosition % mColumns != 0) && (mLists.get((thisPosition - mColumns) - 1) instanceof Boolean)) {
                    mLists.set((thisPosition - mColumns) - 1, mLists.get(thisPosition));
                    mLists.set(thisPosition, false);
                    Log.e("move", "move " + thisPosition + " to left up");
                }
                break;

            }
            case 5: {
                //right up
                if (((thisPosition - mColumns) + 1 >= 0) && ((thisPosition + 1) % mColumns != 0) && (mLists.get((thisPosition - mColumns) + 1) instanceof Boolean)) {
                    mLists.set((thisPosition - mColumns) + 1, mLists.get(thisPosition));
                    mLists.set(thisPosition, false);
                    Log.e("move", "move " + thisPosition + " to right up");
                }
                break;

            }
            case 6: {
                //left down
                if (((thisPosition + mColumns) - 1 < mColumns * mRows) && (thisPosition % mColumns != 0) && (mLists.get((thisPosition + mColumns) - 1) instanceof Boolean)) {
                    mLists.set((thisPosition + mColumns) - 1, mLists.get(thisPosition));
                    mLists.set(thisPosition, false);
                    Log.e("move", "move " + thisPosition + " to left down");
                }
                break;

            }
            case 7: {
                //right down
                if (((thisPosition + mColumns) + 1 < mColumns * mRows) && ((thisPosition + 1) % mColumns != 0) && (mLists.get((thisPosition + mColumns) + 1) instanceof Boolean)) {
                    mLists.set((thisPosition + mColumns) + 1, mLists.get(thisPosition));
                    mLists.set(thisPosition, false);
                    Log.e("move", "move " + thisPosition + " to right down");
                }
                break;

            }
            default:
                break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mUpdatePresenter.update(mLists);
    }
}
