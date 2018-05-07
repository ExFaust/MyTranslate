package com.somename.mytranslate.di

import android.content.Context
import com.somename.data.database.LocalRealmImpl
import com.somename.data.network.LocalApi
import com.somename.data.network.LoggingInterceptor
import com.somename.data.repository.TranslateNetworkRepository
import com.somename.data.repository.TranslateRealmRepository
import com.somename.domain.DBRepository

import com.somename.domain.NetworkRepository
import com.somename.mytranslate.AppDelegate
import com.somename.mytranslate.BuildConfig

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainModule(private val mAppDelegate: AppDelegate) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return mAppDelegate
    }

    @Provides
    @Singleton
    internal fun provideRepository(translateNetworkRepository: TranslateNetworkRepository): NetworkRepository {
        return translateNetworkRepository
    }

    @Provides
    @Singleton
    internal fun provideRealmRepository(translateRealmRepository: TranslateRealmRepository): DBRepository {
        return translateRealmRepository
    }

    @Provides
    @Singleton
    internal fun provideAPI(retrofit: Retrofit): LocalApi {
        return retrofit.create(LocalApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideLocalRealm(realm: Realm): LocalRealmImpl {
        return LocalRealmImpl(realm)
    }

    @Provides
    @Singleton
    internal fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor.create())
                .build()
    }

    @Provides
    @Named("executor_thread")
    internal fun provideExecutorThread(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named("ui_thread")
    internal fun provideUiThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
