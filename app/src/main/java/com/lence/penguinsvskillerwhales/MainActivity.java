package com.lence.penguinsvskillerwhales;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity implements UpdateAdapter {
    int ROWS = 15;
    int COLUMNS = 10;

    @BindView(R.id.lists)
    RecyclerView mTable;
    @BindView(R.id.restart)
    Button mRestart;
    @BindView(R.id.click)
    FrameLayout mClick;
    Adapter mAdapter;
    Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        ButterKnife.bind(this);

        mPresenter = new Presenter(ROWS, COLUMNS, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, COLUMNS);
        mTable.setLayoutManager(mLayoutManager);
        mTable.setNestedScrollingEnabled(false);
        mPresenter.primaryState();
    }

    @OnClick(R.id.restart)
    public void onMRestartClicked() {
        mPresenter.primaryState();

    }

    @Override
    public void updateAdapter(CopyOnWriteArrayList<Object> lists) {

        mAdapter = new Adapter(lists, this, ROWS, COLUMNS);
        mTable.setAdapter(mAdapter);
        mTable.getAdapter().notifyDataSetChanged();
    }

    boolean start=false;
    Handler mHandler;

    @OnClick(R.id.click)
    public void onViewClicked() {
//        mHandler = new Handler() {
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//                if (start) {
//                    mPresenter.nextStep();
//                }
//
//                mHandler.sendEmptyMessageDelayed(0, 100);
//            }
//        };
//
//        start = !start;
//        mHandler.sendEmptyMessage(0);


        mPresenter.nextStep();
    }

    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {
            mPresenter.nextStep();
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
