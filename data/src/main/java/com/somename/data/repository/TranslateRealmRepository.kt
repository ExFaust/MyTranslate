package com.somename.data.repository


import com.somename.data.repository.datasource.RealmDataSource
import com.somename.data.repository.datasource.RealmDataSourceFactory
import com.somename.data.repository.datasource.mapper.WordFromDBEntityMapper
import com.somename.domain.DBRepository
import com.somename.domain.model.WordFromDB

import javax.inject.Inject

import io.reactivex.Observable

class TranslateRealmRepository @Inject
internal constructor(moviesRealmDataSourceFactory: RealmDataSourceFactory, private val mWordFromDBEntityMapper: WordFromDBEntityMapper) : DBRepository {

    private val mDataSource: RealmDataSource = moviesRealmDataSourceFactory.createDataSource()

    override fun addWord(word: WordFromDB): Observable<Boolean> {
        return mDataSource.addWord(mWordFromDBEntityMapper.map(word))
    }

    override fun loadWords(): Observable<List<WordFromDB>> {
        return mDataSource.loadWords().map(mWordFromDBEntityMapper::reverseMap)
    }

    override fun searchWord(word: String): Observable<List<WordFromDB>> {
        return mDataSource.searchWord(word).map(mWordFromDBEntityMapper::reverseMap)
    }
}
