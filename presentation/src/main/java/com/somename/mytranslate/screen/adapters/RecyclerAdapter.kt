package com.somename.mytranslate.screen.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import com.somename.mytranslate.R
import com.somename.mytranslate.content.WordFromDBViewModel
import kotlinx.android.synthetic.main.recycler_item.view.*


class RecyclerAdapter(items: List<WordFromDBViewModel>) : BaseAdapter<RecyclerHolder, WordFromDBViewModel>(items) {

    private var mOnClickListener: OnClickListener? = null

    private val mInternalListener = android.view.View.OnClickListener { view ->
        val position = view.tag as Int
        mOnClickListener!!.onRemoveClick(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        return RecyclerHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.itemRemove.tag = position
        holder.itemView.itemRemove.setOnClickListener(mInternalListener)
        val postViewModel = getItem(position)
        holder.bind(postViewModel)
    }

    fun setOnRemoveClickListener(onItemClickListener: OnClickListener?) {
        mOnClickListener = onItemClickListener
    }

    interface OnClickListener {

        fun onRemoveClick(position: Int)

    }

}
