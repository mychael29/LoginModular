package com.kimochisoft.practicalcaseone.core

import com.kimochisoft.data.local.SharedPreferenceDataSource
import com.kimochisoft.practicalcaseone.core.di.DaggerAppComponent
import com.kimochisoft.practicalcaseone.common.UiContext
import com.kimochisoft.practicalcaseone.common.UserSingleton
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        UiContext.init(applicationContext)
        UserSingleton.init(SharedPreferenceDataSource(applicationContext))
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val androidInjector: AndroidInjector<BaseApplication> =
            DaggerAppComponent.builder().application(this).applicationBase(this).build()
        androidInjector.inject(this)
        return androidInjector
    }
}