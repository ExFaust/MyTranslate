package com.somename.mytranslate

import android.app.Application
import com.somename.mytranslate.di.DaggerMainComponent

import com.somename.mytranslate.di.MainComponent
import com.somename.mytranslate.di.MainModule
import io.realm.Realm

class AppDelegate : Application() {

    var mainComponent: MainComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        mainComponent = DaggerMainComponent.builder().mainModule(MainModule(this)).build()

    }

}
