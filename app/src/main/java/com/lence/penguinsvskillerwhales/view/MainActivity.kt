package com.lence.penguinsvskillerwhales.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.lence.penguinsvskillerwhales.R
import com.lence.penguinsvskillerwhales.life.Presenter

class MainActivity : AppCompatActivity(), UpdateAdapter {
    internal var ROWS = 15
    internal var COLUMNS = 10

    @BindView(R.id.lists)
    lateinit var mTable: RecyclerView
    @BindView(R.id.restart)
    lateinit var mRestart: Button
    @BindView(R.id.click)
    lateinit var mClick: FrameLayout
    internal var mAdapter: Adapter? = null
    internal var mPresenter: Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        ButterKnife.bind(this)
        //        loading = new ProgressDialog(this);
        //        loading.setIndeterminate(true);
        //        loading.setCancelable(false);
        mPresenter = Presenter(ROWS, COLUMNS, this)

        val mLayoutManager = GridLayoutManager(this, COLUMNS)
        mTable.layoutManager = mLayoutManager
        mTable.isNestedScrollingEnabled = false
        mPresenter?.primaryState()
    }

    @OnClick(R.id.restart)
    fun onMRestartClicked() {
        mPresenter?.primaryState()

    }

    override fun updateAdapter(field: Field) {
        //mTable.setAdapter(mAdapter);
        mTable.adapter.notifyDataSetChanged()
        // loading.dismiss();
    }

    override fun createAdapter(field: Field) {
        mAdapter = Adapter(field, this, ROWS, COLUMNS)
        mTable.adapter = mAdapter
        mTable.setHasFixedSize(true)
        mTable.adapter.notifyDataSetChanged()
    }


    @OnClick(R.id.click)
    fun onViewClicked() {
        // loading.show();
        mPresenter?.nextStep()
    }


    override fun onStop() {
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
