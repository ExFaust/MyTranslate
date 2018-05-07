package com.somename.mytranslate.screen.adapters

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View

import java.util.ArrayList


abstract class BaseAdapter<VH : RecyclerView.ViewHolder, T>(items: List<T>) : RecyclerView.Adapter<VH>() {

    private val mItems = ArrayList<T>()

    private var mOnItemClickListener: OnItemClickListener? = null

    private val mInternalListener = { view:View ->
        if (mOnItemClickListener != null) {
            val position = view.tag as Int
            mOnItemClickListener!!.onItemClick(position)
        }
    }

    private var mRecyclerView: RecyclerView? = null

    init {
        mItems.addAll(items)
    }

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
        mRecyclerView!!.adapter = this
        refreshRecycler()
    }

    fun add(value: T) {
        mItems.add(value)
        refreshRecycler()
    }

    fun changeDataSet(values: List<T>) {
        mItems.clear()
        mItems.addAll(values)
        refreshRecycler()
    }

    fun clear() {
        mItems.clear()
        refreshRecycler()
    }

    fun refreshRecycler() {
        notifyDataSetChanged()
    }

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(mInternalListener)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }

    fun getItem(position: Int): T {
        return mItems[position]
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    interface OnItemClickListener {

        fun onItemClick(position: Int)

    }

}
