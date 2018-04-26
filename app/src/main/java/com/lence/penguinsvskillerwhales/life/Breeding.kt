package com.lence.penguinsvskillerwhales.life

import android.graphics.Point
import com.lence.penguinsvskillerwhales.model.Organism
import com.lence.penguinsvskillerwhales.utils.Constants
import com.lence.penguinsvskillerwhales.view.Field
import com.lence.penguinsvskillerwhales.view.UpdatePresenter
import java.util.*

class Breeding internal constructor(private val mField: Field,
                                    private val mCurrent: Point,
                                    private val mUpdatePresenter: UpdatePresenter,
                                    private val mOrganism: Organism) {

    fun execute() {
        val checkList = ArrayList<Int>()
        //free directions list
        for (direction in Constants.directions) {
            if (findDirection(direction)) {
                checkList.add(direction)
            }
        }
        if (checkList.size > 0) {
            val randomNumber: Int
            if (checkList.size == 1) {
                randomNumber = 0
            } else {
                val random = Random()
                randomNumber = random.nextInt(checkList.size - 1)
            }
            //creation of a new organism
            mField[Field.getPointByDirection(mCurrent.x, mCurrent.y,
                    checkList[randomNumber])] = mOrganism
        }
        mUpdatePresenter.update(mField)
    }

    private fun findDirection(direction: Int): Boolean {
        val p = Field.getPointByDirection(mCurrent.x, mCurrent.y, direction)

        return mField.isInField(p.x, p.y) && mField[p.x, p.y] == null

    }
}
