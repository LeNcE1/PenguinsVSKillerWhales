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

//        mLists.set(0, new Penguin());
//        mLists.set(1, new Penguin());
//            mLists.set(10, new Orca());
//        mLists.set(11, new Penguin());
//        mLists.set(33, new Orca());
//        mLists.set(44, new Penguin());
//        mLists.set(55, new Penguin());
//        mLists.set(66, new Penguin());
//        mLists.set(27, new Orca());
//        mLists.set(18, new Penguin());
//        mLists.set(98, new Penguin());
//        mLists.set(108, new Penguin());
//        mLists.set(118, new Penguin());
//        mLists.set(128, new Penguin());
//        mLists.set(138, new Penguin());
        mUpdateAdapter.updateAdapter(mLists);
    }


    public void nextStep() {
        for (int position = 0; position < mLists.size(); position++) {
            if (!(mLists.get(position) instanceof Boolean)) {

                if (mLists.get(position) instanceof Penguin) {
                    Log.e("lifeCycle", "Penguin " + position);
                    Penguin penguin = (Penguin) mLists.get(position);
                    if (!penguin.isMoved()) {
                        Log.e("lifeCycle", "isMoved " + position);
                        if (penguin.getAge() == 2) {
                            Log.e("lifeCycle", "getAge " + position);
                            //1
                            breeding(position, new Penguin());
                            Log.e("lifeCycle", "breeding " + position);
                        }
                        Log.e("lifeCycle", "premove " + position);
                        move(position);
                        Log.e("lifeCycle", "postmove " + position);
                        penguin.addAge();
                        Log.e("lifeCycle", "addAge " + position);
                        penguin.setMoved(true);
                        Log.e("lifeCycle", "setMoved " + position);
                    }
                }
                if (mLists.get(position) instanceof Orca) {
                    Log.e("lifeCycle", "Orca " + position);
                    Orca orca = (Orca) mLists.get(position);
                    if (!orca.isMoved()) {
                        Log.e("lifeCycle", "isMoved " + position);
                        if (orca.getHunger() < 2) {
                            Log.e("lifeCycle", "getHunger " + position);
                            if (orca.getAge() == 7) {
                                Log.e("lifeCycle", "getAge " + position);
                                breeding(position, new Orca());
                                Log.e("lifeCycle", "breeding " + position);
                            }
                            if (eat(position)) {
                                Log.e("lifeCycle", "eat " + position);
                                orca.eat();
                                Log.e("lifeCycle", "finaleat " + position);
                            } else {
                                Log.e("lifeCycle", "premove " + position);
                                move(position);
                                Log.e("lifeCycle", "postmove " + position);
                                orca.addHunger();
                                Log.e("lifeCycle", "addHunger " + position);
                            }
                            orca.addAge();
                            Log.e("lifeCycle", "addAge " + position);
                            orca.setMoved(true);
                            Log.e("lifeCycle", "setMoved " + position);
                        } else {
                            mLists.set(position, false);
                            Log.e("lifeCycle", "death " + position);
                        }
                    }

                }

            }
        }
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
        // Log.e("move", "move ");
    }


    private void move(int thisPosition) {
        // Log.e("move", "move "+thisPosition);
        switch (mRandom.nextInt(7)) {
            //switch (7) {
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

            Log.e("breeding", "breeding" + thisPosition + " " + randomNumber);
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



