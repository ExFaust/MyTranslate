package com.somename.data.repository.datasource

import com.somename.data.content.WordFromDBEntity
import com.somename.data.database.LocalRealm
import io.reactivex.Observable

class RealmDataSource(private val mLocalApi: LocalRealm) : DBDataSource {

    override fun addWord(word: WordFromDBEntity): Observable<Boolean> {
        return mLocalApi.addWord(word)
    }

    override fun loadWords(): Observable<List<WordFromDBEntity>> {
        return mLocalApi.loadWords()
    }

    override fun searchWord(word: String): Observable<List<WordFromDBEntity>> {
        return mLocalApi.searchWord(word)
    }

    override fun removeWord(word: WordFromDBEntity): Observable<List<WordFromDBEntity>> {
        return mLocalApi.removeWord(word)
    }

}