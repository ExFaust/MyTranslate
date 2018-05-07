package com.somename.data.repository.datasource

import com.somename.data.content.WordFromTranslatorEntity

import io.reactivex.Observable

interface DataSource {

    fun getTranslate(text: String, lang: String): Observable<WordFromTranslatorEntity>

}
