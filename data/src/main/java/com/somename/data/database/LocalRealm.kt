package com.somename.data.database

import com.somename.data.content.WordFromDBEntity
import io.reactivex.Observable

interface LocalRealm {

    fun addWord(word: WordFromDBEntity): Observable<Boolean>

    fun loadWords(): Observable<List<WordFromDBEntity>>

    fun searchWord(word: String): Observable<List<WordFromDBEntity>>
}