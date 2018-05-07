package com.somename.data.network


import com.somename.data.BuildConfig
import com.somename.data.content.WordFromTranslatorEntity

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LocalApi {

    @GET("translate?" + BuildConfig.API_KEY)
    fun getTranslate(@Query("text") text: String,@Query("lang") lang: String): Observable<WordFromTranslatorEntity>

}