package com.somename.domain


import com.somename.domain.model.WordFromTranslator

import io.reactivex.Observable

interface NetworkRepository {

    fun getTranslate(word: String, lang: String): Observable<WordFromTranslator>

}
