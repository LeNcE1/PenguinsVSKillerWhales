package com.lence.penguinsvskillerwhales.life

import android.graphics.Point

import com.lence.penguinsvskillerwhales.model.Orca
import com.lence.penguinsvskillerwhales.model.Penguin
import com.lence.penguinsvskillerwhales.utils.Constants
import com.lence.penguinsvskillerwhales.view.Field
import com.lence.penguinsvskillerwhales.view.UpdatePresenter

class Eat internal constructor(private val mField: Field,
                               private val mCurrent: Point,
                               private val mUpdatePresenter: UpdatePresenter,
                               private val mOrca: Orca) {

    fun execute() {
        //check of all directions

        for (direction in Constants.directions) {
            if (findPenguin(direction)) {
                eat(Field.getPointByDirection(mCurrent.x, mCurrent.y, direction))
                mOrca.eat()
                mUpdatePresenter.update(mField)
            }
        }
        mOrca.addHunger()
        Move(mUpdatePresenter, mField, mCurrent).execute()
    }


    private fun eat(to: Point) {
        mField[to.x, to.y] = mField[mCurrent.x, mCurrent.y]
        mField[mCurrent.x, mCurrent.y] = null
    }

    private fun findPenguin(direction: Int): Boolean {
        val p = Field.getPointByDirection(mCurrent.x, mCurrent.y, direction)
        return mField.isInField(p.x, p.y) && mField[p.x, p.y] is Penguin
    }
}

