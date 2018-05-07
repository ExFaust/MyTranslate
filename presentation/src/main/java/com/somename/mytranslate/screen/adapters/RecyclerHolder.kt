package com.somename.mytranslate.screen.adapters


import android.support.v7.widget.RecyclerView
import android.view.View

import com.somename.mytranslate.content.WordFromDBViewModel
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    fun bind(wordFromDBViewModel: WordFromDBViewModel) {
        mItemView.itemName.text = wordFromDBViewModel.text
        mItemView.itemTranslate.text = wordFromDBViewModel.translatedText
        mItemView.itemLangIn.text = wordFromDBViewModel.langWord
        mItemView.itemLangOut.text = wordFromDBViewModel.langTranslatedWord
    }
}
