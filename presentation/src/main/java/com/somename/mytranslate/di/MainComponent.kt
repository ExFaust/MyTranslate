package com.somename.mytranslate.di

import com.somename.mytranslate.screen.activity.ScrollingActivity
import com.somename.mytranslate.screen.activity.TranslateActivity

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [(MainModule::class)])
interface MainComponent {

    fun inject(scrollingActivity: ScrollingActivity)

    fun inject(translateActivity: TranslateActivity)

}
