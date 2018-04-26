package com.lence.penguinsvskillerwhales.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.lence.penguinsvskillerwhales.R
import com.lence.penguinsvskillerwhales.model.Orca
import com.lence.penguinsvskillerwhales.model.Penguin
import com.squareup.picasso.Picasso

class Adapter internal constructor(private val mField: Field,
                                   private val mContext: Context,
                                   private val mRows: Int,
                                   private val mColumns: Int) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, parent.measuredHeight / mRows + 1)
        itemView.layoutParams = layoutParams
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mField[position % mColumns, position / mColumns] is Penguin) {
            //holder.mItem.setImageBitmap(tux);
            //.setImageResource(R.drawable.tux);
            Picasso.with(mContext).load(R.drawable.tux).into(holder.mItem)
        } else
        if (mField[position % mColumns, position / mColumns] is Orca) {
            Picasso.with(mContext).load(R.drawable.orca).into(holder.mItem)
        } else
        if (mField[position % mColumns, position / mColumns] == null) {
            holder.mItem.setImageResource(R.color.colorPrimary)
        }
    }


    override fun getItemCount(): Int {
        return mRows * mColumns
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.item)
        lateinit var mItem: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
