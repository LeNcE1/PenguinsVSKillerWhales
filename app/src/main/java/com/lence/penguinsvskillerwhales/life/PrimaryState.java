package com.lence.penguinsvskillerwhales.life;

import com.lence.penguinsvskillerwhales.model.Orca;
import com.lence.penguinsvskillerwhales.model.Penguin;
import com.lence.penguinsvskillerwhales.view.KotlinField;
import com.lence.penguinsvskillerwhales.view.UpdateAdapter;
import com.lence.penguinsvskillerwhales.view.UpdatePresenter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class PrimaryState {
    private int mRows;
    private int mColumns;
    private UpdatePresenter mUpdatePresenter;
    //private List<Organism> mLists;
    private KotlinField mField;
    private UpdateAdapter mUpdateAdapter;

    PrimaryState(int rows, int columns, UpdatePresenter updatePresenter, UpdateAdapter updateAdapter, KotlinField field) {

        mRows = rows;
        mColumns = columns;
        mUpdatePresenter = updatePresenter;
        mUpdateAdapter = updateAdapter;
        mField = field;
    }

    public void execute() {
        //Field.setmLists(Collections.synchronizedList(new ArrayList<Organism>()));
        mField = new KotlinField(mRows, mColumns);
        if (mRows * mColumns <= 1) {
            mField.add(new Penguin(mUpdatePresenter));
        } else if (mRows * mColumns == 2) {
            mField.add(new Penguin(mUpdatePresenter));
            mField.add(new Orca(mUpdatePresenter));
        } else {
            for (int y = 0; y < mRows; y++)
                for (int x = 0; x < mColumns; x++) {
                    mField.add(null);
                }
            Set<Integer> generated = new HashSet<>();
            int generatedCount = (mRows * mColumns) / 2;
            if (mRows * mColumns / 20 > 0) {
                generatedCount += ((mRows * mColumns) / 20);
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
                int position = iterator.next();
                mField.set( position % mColumns,position / mColumns, new Penguin(mUpdatePresenter));
            }
            for (; generatedIndex < generatedCount; generatedIndex++) {
                int position = iterator.next();
                mField.set( position % mColumns,position / mColumns, new Orca(mUpdatePresenter));
            }
        }
        mUpdatePresenter.update(mField);
        mUpdateAdapter.createAdapter(mField);
    }

}
