package com.lence.penguinsvskillerwhales;

import android.os.AsyncTask;

import com.lence.penguinsvskillerwhales.model.Orca;
import com.lence.penguinsvskillerwhales.model.Penguin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrimaryState extends AsyncTask<Void, Void, Void> {
    private int mRows;
    private int mColumns;
    private UpdatePresenter mUpdatePresenter;
    private CopyOnWriteArrayList<Object> mLists;
    private UpdateAdapter mUpdateAdapter;

    public PrimaryState(int rows, int columns, UpdatePresenter updatePresenter, UpdateAdapter updateAdapter) {

        mRows = rows;
        mColumns = columns;
        mUpdatePresenter = updatePresenter;
        mUpdateAdapter = updateAdapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mLists = new CopyOnWriteArrayList<>(new LinkedList<>());
        if (mRows * mColumns <= 1) {
            mLists.add(new Penguin());
        } else if (mRows * mColumns == 2) {
            mLists.add(new Penguin());
            mLists.add(new Orca());
        } else {
            for (int i = 0; i < mRows * mColumns; i++) {

                mLists.add(false);
            }
            Set<Integer> generated = new HashSet<>();
            int generatedCount = (mRows * mColumns) / 2;
            if (mRows * mColumns / 20 > 0) {
                generatedCount += mRows * mColumns / 20;
            } else {
                //if table is small then add 1 orcas
                generatedCount += 1;
            }
            Random random = new Random();
            while (generated.size() < generatedCount) {
                generated.add(random.nextInt((mRows * mColumns) - 1));
            }
            Iterator<Integer> iterator = generated.iterator();
            int generatedIndex = 0;
            for (; generatedIndex < (mRows * mColumns) / 2; generatedIndex++) {

                mLists.set(iterator.next(), new Penguin());
            }
            for (; generatedIndex < generatedCount; generatedIndex++) {
                mLists.set(iterator.next(), new Orca());
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mUpdatePresenter.update(mLists);
        mUpdateAdapter.updateAdapter(mLists);
    }
}
