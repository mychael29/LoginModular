package com.kimochisoft.practicalcaseone.core.di

import com.kimochisoft.practicalcaseone.core.di.modules.auth.AuthScope
import com.kimochisoft.practicalcaseone.core.di.modules.auth.AuthViewModelsModule
import com.kimochisoft.practicalcaseone.ui.auth.logIn.LogInActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class])
    abstract fun contributeAuthActivity(): LogInActivity?
}