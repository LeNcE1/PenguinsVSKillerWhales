package com.lence.penguinsvskillerwhales.life

import android.graphics.Point
import com.lence.penguinsvskillerwhales.utils.Constants
import com.lence.penguinsvskillerwhales.view.Field
import com.lence.penguinsvskillerwhales.view.UpdatePresenter
import java.util.*

class Move internal constructor(private val mUpdatePresenter: UpdatePresenter,
                                private val mField: Field,
                                private val mCurrent: Point) {


    fun execute() {
        val random = Random()
        val randomInt = random.nextInt(Constants.count_choice)

        if (findDirection(randomInt)) {
            move(Field.getPointByDirection(mCurrent.x, mCurrent.y, randomInt))
        }

        mUpdatePresenter.update(mField)
    }


    private fun move(to: Point) {
        mField[to.x, to.y] = mField[mCurrent.x, mCurrent.y]
        mField[mCurrent.x, mCurrent.y] = null
        //Log.e("move", "move " + to + " to right down");
    }

    private fun findDirection(direction: Int): Boolean {
        val p = Field.getPointByDirection(mCurrent.x, mCurrent.y, direction)
        //Log.e("point",p +" "+ mField.isInField(p.x, p.y));
        return mField.isInField(p.x, p.y) && mField[p.x, p.y] == null

    }


}
