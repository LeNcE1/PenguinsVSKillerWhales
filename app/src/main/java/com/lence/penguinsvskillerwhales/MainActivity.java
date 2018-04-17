package com.lence.penguinsvskillerwhales;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.LinkedList;

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

    Adapter mAdapter;
    Presenter mPresenter;
    @BindView(R.id.click)
    FrameLayout mClick;

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
        // mTable.setItemAnimator(new DefaultItemAnimator());
        mPresenter.primaryState();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //    @OnTouch(R.id.lists)
//    public boolean onMListsClicked() {
//        mPresenter.nextStep();
//        return true;
//    }


    @OnClick(R.id.restart)
    public void onMRestartClicked() {
        mPresenter.primaryState();

    }

    @Override
    public void updateAdapter(LinkedList<Object> lists) {
        mAdapter = new Adapter(lists, this, ROWS, COLUMNS);
        mTable.setAdapter(mAdapter);
        mTable.getAdapter().notifyDataSetChanged();
    }

    @OnClick(R.id.click)
    public void onViewClicked() {
        mPresenter.nextStep();
    }
}
