package com.lence.penguinsvskillerwhales.life

import android.graphics.Point

import com.lence.penguinsvskillerwhales.model.Orca
import com.lence.penguinsvskillerwhales.model.Organism
import com.lence.penguinsvskillerwhales.view.Field
import com.lence.penguinsvskillerwhales.view.UpdateAdapter
import com.lence.penguinsvskillerwhales.view.UpdatePresenter

class Presenter
//private List<Organism> mLists;

(private val mRows: Int, private val mColumns: Int, private val mUpdateAdapter: UpdateAdapter) : UpdatePresenter {
    private var mField: Field? = null

    //initial condition - 50% of penguins, 5% of orcas
    fun primaryState() {

        PrimaryState(mRows, mColumns, this, mUpdateAdapter, mField).execute()

    }

    fun nextStep() {
        // Log.e("nextStep", "nextStep");
        NextStep(mRows, mColumns, mUpdateAdapter, mField!!).execute()
    }


    override fun move(thisPoint: Point) {
        Move(this, mField!!, thisPoint).execute()
    }

    override fun breeding(thisPoint: Point, organism: Organism) {
        Breeding(mField!!, thisPoint, this, organism).execute()
    }

    override fun eat(thisPoint: Point, orca: Orca) {
        Eat(mField!!, thisPoint, this, orca).execute()
    }

    override fun death(thisPoint: Point) {
        mField!![thisPoint] = null
    }

    override fun update(list: Field) {
        mField = list
    }
}



