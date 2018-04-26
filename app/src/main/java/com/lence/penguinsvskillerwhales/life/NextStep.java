package com.lence.penguinsvskillerwhales.life;

import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;

import com.lence.penguinsvskillerwhales.view.KotlinField;
import com.lence.penguinsvskillerwhales.view.UpdateAdapter;

import java.util.Objects;

public class NextStep extends AsyncTask<Void, Void, Void> {
    private int mRows;//15
    private int mColumns;//10
    private UpdateAdapter mUpdateAdapter;
    private KotlinField mField;
    private long time;

    NextStep(int mRows, int mColumns, UpdateAdapter mUpdateAdapter, KotlinField mField) {
        this.mRows = mRows;
        this.mColumns = mColumns;
        this.mUpdateAdapter = mUpdateAdapter;
        this.mField = mField;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        time = System.currentTimeMillis();
        if (mRows * mColumns > 1) {
            for (int y = 0; y < mRows; y++)
                for (int x = 0; x < mColumns; x++) {
                    if (!(mField.get(x, y) == null)) {
                        Objects.requireNonNull(mField.get(x, y)).lifeCycle(new Point(x, y));
                    }
                }
        }
        //preparation for the next step
        for (int y = 0; y < mRows; y++)
            for (int x = 0; x < mColumns; x++) {
                if (mField.get(x, y) != null) {
                    Objects.requireNonNull(mField.get(x, y)).setMoved(false);
                }
            }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mUpdateAdapter.updateAdapter(mField);
        Log.e("TimeTime", "" + (System.currentTimeMillis() - time));
    }
}
