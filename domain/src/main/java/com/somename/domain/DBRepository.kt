package com.somename.domain

import com.somename.domain.model.WordFromDB
import io.reactivex.Observable

interface DBRepository {

    fun addWord(word: WordFromDB): Observable<Boolean>

    fun loadWords(): Observable<List<WordFromDB>>

    fun searchWord(word: String): Observable<List<WordFromDB>>

    fun removeWord(word: WordFromDB): Observable<List<WordFromDB>>

}