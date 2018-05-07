package com.somename.data.repository.datasource

import com.somename.data.network.LocalApi
import com.somename.data.content.WordFromTranslatorEntity

import io.reactivex.Observable

class APIDataSource(private val mLocalApi: LocalApi) : DataSource {

    override fun getTranslate(text: String, lang: String): Observable<WordFromTranslatorEntity> {
        return mLocalApi.getTranslate(text, lang)
    }

}
