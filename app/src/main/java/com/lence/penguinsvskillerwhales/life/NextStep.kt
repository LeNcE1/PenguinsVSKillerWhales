package com.lence.penguinsvskillerwhales.life

import android.graphics.Point
import android.os.AsyncTask
import android.util.Log
import com.lence.penguinsvskillerwhales.view.Field
import com.lence.penguinsvskillerwhales.view.UpdateAdapter

class NextStep internal constructor(private val mRows: Int,
                                    private val mColumns: Int,
                                    private val mUpdateAdapter: UpdateAdapter,
                                    private val mField: Field) : AsyncTask<Void, Void, Void>() {
    private var time: Long = 0

    override fun doInBackground(vararg voids: Void): Void? {
        time = System.currentTimeMillis()
        if (mRows * mColumns > 1) {
            for (y in 0 until mRows)
                for (x in 0 until mColumns) {
                    if (mField[x, y] != null) {
                        mField[x, y]?.lifeCycle(Point(x, y))
                    }
                }
        }
        //preparation for the next step
        for (y in 0 until mRows)
            for (x in 0 until mColumns) {
                    mField[x, y]?.moved=false

            }

        return null
    }

    override fun onPostExecute(aVoid: Void?) {
        super.onPostExecute(aVoid)
        mUpdateAdapter.updateAdapter(mField)
        Log.e("TimeTime", "" + (System.currentTimeMillis() - time))
    }
}
