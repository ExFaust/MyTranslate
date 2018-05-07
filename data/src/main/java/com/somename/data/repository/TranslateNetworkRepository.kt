package com.somename.data.repository

import com.somename.data.repository.datasource.DataSource
import com.somename.data.repository.datasource.APIDataSourceFactory
import com.somename.data.repository.datasource.mapper.WordEntityMapper
import com.somename.domain.NetworkRepository
import com.somename.domain.model.WordFromTranslator

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Observable
import io.reactivex.annotations.NonNull

@Singleton
class TranslateNetworkRepository @Inject
internal constructor(@NonNull APIDataSourceFactory: APIDataSourceFactory, @param:NonNull private val mWordEntityMapper: WordEntityMapper) : NetworkRepository {

    private val mDataSource: DataSource = APIDataSourceFactory.createDataSource()

    override fun getTranslate(word: String, lang: String): Observable<WordFromTranslator> {
        return mDataSource.getTranslate(word,lang).map(mWordEntityMapper::reverseMap)
    }

}
