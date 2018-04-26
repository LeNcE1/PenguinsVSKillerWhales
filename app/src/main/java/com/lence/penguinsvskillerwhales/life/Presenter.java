package com.lence.penguinsvskillerwhales.life;

import android.graphics.Point;

import com.lence.penguinsvskillerwhales.model.Orca;
import com.lence.penguinsvskillerwhales.model.Organism;
import com.lence.penguinsvskillerwhales.view.KotlinField;
import com.lence.penguinsvskillerwhales.view.UpdateAdapter;
import com.lence.penguinsvskillerwhales.view.UpdatePresenter;

public class Presenter implements UpdatePresenter {
    private int mRows;//15
    private int mColumns;//10
    private UpdateAdapter mUpdateAdapter;
    private KotlinField mField;
    //private List<Organism> mLists;

    public Presenter(int rows, int columns, UpdateAdapter updateAdapter) {
        mRows = rows;
        mColumns = columns;
        mUpdateAdapter = updateAdapter;
    }

    //initial condition - 50% of penguins, 5% of orcas
    public void primaryState() {

        new PrimaryState(mRows, mColumns, this, mUpdateAdapter, mField).execute();

    }

    public void nextStep() {
       // Log.e("nextStep", "nextStep");
        new NextStep(mRows,mColumns,mUpdateAdapter,mField).execute();
    }



    @Override
    public void move(Point thisPoint) {
        new Move(this,mField,thisPoint).execute();
    }

    @Override
    public void breeding(Point thisPoint, Organism organism) {
        new Breeding(mField,thisPoint,this, organism).execute();
    }

    @Override
    public void eat(Point thisPoint, Orca orca) {
        new Eat(mField,thisPoint,this, orca).execute();
    }

    @Override
    public void death(Point thisPoint) {
        mField.set(thisPoint, null);
    }

    @Override
    public void update(KotlinField field) {
        mField = field;
    }
}



