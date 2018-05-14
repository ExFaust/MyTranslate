package com.somename.data.repository.datasource

import com.somename.data.content.WordFromDBEntity
import io.reactivex.Observable

interface DBDataSource {

    fun addWord(word: WordFromDBEntity): Observable<Boolean>

    fun loadWords(): Observable<List<WordFromDBEntity>>

    fun searchWord(word: String): Observable<List<WordFromDBEntity>>

    fun removeWord(word: WordFromDBEntity): Observable<List<WordFromDBEntity>>

}