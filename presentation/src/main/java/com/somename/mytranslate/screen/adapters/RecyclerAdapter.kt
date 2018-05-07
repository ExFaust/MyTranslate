package com.somename.mytranslate.screen.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import com.somename.mytranslate.R
import com.somename.mytranslate.content.WordFromDBViewModel


class RecyclerAdapter(items: List<WordFromDBViewModel>) : BaseAdapter<RecyclerHolder, WordFromDBViewModel>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        return RecyclerHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val postViewModel = getItem(position)
        holder.bind(postViewModel)
    }

}
