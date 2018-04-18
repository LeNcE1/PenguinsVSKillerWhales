package com.lence.penguinsvskillerwhales;

import android.util.Log;

import com.lence.penguinsvskillerwhales.model.Orca;
import com.lence.penguinsvskillerwhales.model.Organism;
import com.lence.penguinsvskillerwhales.model.Penguin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Presenter implements UpdatePresenter {
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
        new PrimaryState(mRows, mColumns, this).execute();
    }


    public void nextStep() {
        if (mRows * mColumns > 1) {
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
                                eat(position);
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
    }


    private void move(int thisPosition) {
        new Move(mRows, mColumns, this, mLists, thisPosition).execute();
    }

    private void breeding(int thisPosition, Organism organism) {
        new Breeding(mRows, mColumns, this, mLists, thisPosition, organism).execute();
    }

    private void eat(int thisPosition) {
        new Eat(mRows, mColumns, this, mLists, thisPosition).execute();
    }

    @Override
    public void update(LinkedList<Object> list) {
        mLists = list;
        mUpdateAdapter.updateAdapter(list);
    }
}



