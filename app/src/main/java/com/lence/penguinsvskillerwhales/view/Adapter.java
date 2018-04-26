package com.lence.penguinsvskillerwhales.view;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lence.penguinsvskillerwhales.R;
import com.lence.penguinsvskillerwhales.model.Orca;

import com.lence.penguinsvskillerwhales.model.Organism;
import com.lence.penguinsvskillerwhales.model.Penguin;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private KotlinField mField;
    private Context mContext;
    private int mRows;
    private int mColumns;

    Adapter(KotlinField field, Context context, int rows, int columns) {
        mField = field;
        mContext = context;
        mRows = rows;
        mColumns = columns;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (parent.getMeasuredHeight() / mRows) + 1);
        itemView.setLayoutParams(layoutParams);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mField.get(position % mColumns, position / mColumns) instanceof Penguin) {
            //holder.mItem.setImageBitmap(tux);
                    //.setImageResource(R.drawable.tux);
            Picasso.with(mContext).load(R.drawable.tux).into(holder.mItem);
        }
        if (mField.get(position % mColumns, position / mColumns) instanceof Orca) {
            Picasso.with(mContext).load(R.drawable.orca).into(holder.mItem);
        }
        if (mField.get(position % mColumns, position / mColumns) == null) {
            holder.mItem.setImageResource(R.color.colorPrimary);
        }
    }


    @Override
    public int getItemCount() {
        return mRows * mColumns;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item)
        ImageView mItem;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
