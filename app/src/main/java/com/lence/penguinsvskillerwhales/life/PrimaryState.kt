package com.lence.penguinsvskillerwhales.life

import com.lence.penguinsvskillerwhales.model.Orca
import com.lence.penguinsvskillerwhales.model.Penguin
import com.lence.penguinsvskillerwhales.view.Field
import com.lence.penguinsvskillerwhales.view.UpdateAdapter
import com.lence.penguinsvskillerwhales.view.UpdatePresenter
import java.util.*

class PrimaryState internal constructor(private val mRows: Int,
                                        private val mColumns: Int,
                                        private val mUpdatePresenter: UpdatePresenter,
                                        private val mUpdateAdapter: UpdateAdapter,
                                        private var mField: Field?) {

    fun execute() {
        mField = Field(mRows, mColumns)
        if (mRows * mColumns <= 1) {
            mField!!.add(Penguin(mUpdatePresenter))
        } else if (mRows * mColumns == 2) {
            mField!!.add(Penguin(mUpdatePresenter))
            mField!!.add(Orca(mUpdatePresenter))
        } else {
            for (y in 0 until mRows)
                for (x in 0 until mColumns) {
                    mField!!.add(null)
                }
            val generated = HashSet<Int>()
            var generatedCount = mRows * mColumns / 2
            if (mRows * mColumns / 20 > 0) {
                generatedCount += mRows * mColumns / 20
            } else {
                //if table is small then add 1 orcas
                generatedCount += 1
            }
            val random = Random()
            while (generated.size < generatedCount) {
                generated.add(random.nextInt(mRows * mColumns - 1))
            }
            val iterator = generated.iterator()
            var generatedIndex = 0
            while (generatedIndex < mRows * mColumns / 2) {
                val position = iterator.next()
                mField!![position % mColumns, position / mColumns] = Penguin(mUpdatePresenter)
                generatedIndex++
            }
            while (generatedIndex < generatedCount) {
                val position = iterator.next()
                mField!![position % mColumns, position / mColumns] = Orca(mUpdatePresenter)
                generatedIndex++
            }
        }
        mUpdatePresenter.update(mField!!)
        mUpdateAdapter.createAdapter(mField!!)
    }

}
