package com.lence.penguinsvskillerwhales;

import android.util.Log;

import com.lence.penguinsvskillerwhales.model.Orca;
import com.lence.penguinsvskillerwhales.model.Organism;
import com.lence.penguinsvskillerwhales.model.Penguin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Presenter {
    private int mRows;
    private int mColumns;
    private UpdateAdapter mUpdateAdapter;

    private LinkedList<Object> mLists;

    private Random mRandom = new Random();

    Presenter(int rows, int columns, UpdateAdapter updateAdapter) {
        mRows = rows;
        mColumns = columns;
        mUpdateAdapter = updateAdapter;
    }

    //initial condition - 50% of penguins, 5% of orcas
    void primaryState() {
        mLists = new LinkedList<>();
        for (int i = 0; i < mRows * mColumns; i++) {

            mLists.add(false);
        }
        for (int i = 0; i < (mRows * mColumns) / 2; i++) {
            int nextPosition = mRandom.nextInt((mRows * mColumns) - 1);
            while (!(mLists.get(nextPosition) instanceof Boolean)) {
                nextPosition = mRandom.nextInt((mRows * mColumns) - 1);
            }
            mLists.set(nextPosition, new Penguin());
        }
        //if table is small then add 1 orcas
        if (mRows * mColumns / 20 > 0) {
            for (int i = 0; i < (mRows * mColumns) / 20; i++) {
                int nextPosition = mRandom.nextInt((mRows * mColumns) - 1);
                while (!(mLists.get(nextPosition) instanceof Boolean)) {
                    nextPosition = mRandom.nextInt((mRows * mColumns) - 1);
                }
                mLists.set(nextPosition, new Orca());
            }
        } else {
            int nextPosition = mRandom.nextInt((mRows * mColumns) - 1);
            while (!(mLists.get(nextPosition) instanceof Boolean)) {
                nextPosition = mRandom.nextInt((mRows * mColumns) - 1);
            }
            mLists.set(nextPosition, new Orca());
        }

        mUpdateAdapter.updateAdapter(mLists);
    }


    public void nextStep() {
        for (int position = 0; position < mLists.size(); position++) {
            //type check
            if (!(mLists.get(position) instanceof Boolean)) {
                //penguin life cycle
                if (mLists.get(position) instanceof Penguin) {
                    Penguin penguin = (Penguin) mLists.get(position);
                    if (!penguin.isMoved()) {
                        if (penguin.getAge() == 2) {
                            breeding(position, new Penguin());
                        }
                        move(position);
                        penguin.addAge();
                        penguin.setMoved(true);
                    }
                }
                //orca life cycle
                if (mLists.get(position) instanceof Orca) {
                    Orca orca = (Orca) mLists.get(position);
                    if (!orca.isMoved()) {
                        if (orca.getHunger() < 2) {
                            if (orca.getAge() == 7) {
                                breeding(position, new Orca());
                            }
                            if (eat(position)) {
                                orca.eat();
                            } else {
                                move(position);
                                orca.addHunger();
                            }
                            orca.addAge();
                            orca.setMoved(true);
                        } else {
                            mLists.set(position, false);
                        }
                    }

                }

            }
        }
        //preparation for the next step
        for (int position = 0; position < mLists.size(); position++) {
            if (!(mLists.get(position) instanceof Boolean)) {

                if (mLists.get(position) instanceof Penguin) {
                    Penguin penguin = (Penguin) mLists.get(position);
                    if (penguin.isMoved()) {
                        penguin.setMoved(false);
                    }
                }
                if (mLists.get(position) instanceof Orca) {
                    Orca orca = (Orca) mLists.get(position);
                    if (orca.isMoved()) {
                        orca.setMoved(false);
                    }
                }

            }
        }
        mUpdateAdapter.updateAdapter(mLists);
    }


    private void move(int thisPosition) {
        switch (mRandom.nextInt(7)) {
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

    }

    private void breeding(int thisPosition, Organism organism) {
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
                randomNumber = mRandom.nextInt(checkList.size() - 1);
            }
            //creation of a new organism
            switch (checkList.get(randomNumber)) {
                case 0: {
                    mLists.set(thisPosition - 1, organism);
                    break;
                }
                case 1: {
                    mLists.set(thisPosition + 1, organism);
                    break;
                }
                case 2: {
                    mLists.set(thisPosition - mColumns, organism);
                    break;
                }
                case 3: {
                    mLists.set(thisPosition + mColumns, organism);
                    break;
                }
                case 4: {
                    mLists.set((thisPosition - mColumns) - 1, organism);
                    break;
                }
                case 5: {
                    mLists.set((thisPosition - mColumns) + 1, organism);
                    break;
                }
                case 6: {
                    mLists.set((thisPosition + mColumns) - 1, organism);
                    break;
                }
                case 7: {
                    mLists.set((thisPosition + mColumns) + 1, organism);
                    break;
                }
            }
        }
    }

    private boolean eat(int thisPosition) {
        //verification of all directions
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
}



