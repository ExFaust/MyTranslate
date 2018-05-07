package com.somename.data.repository.datasource

import com.somename.data.network.LocalApi

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIDataSourceFactory @Inject
constructor(private val mLocalAPI: LocalApi) {

    fun createDataSource(): DataSource {
        return APIDataSource(mLocalAPI)
    }
}
