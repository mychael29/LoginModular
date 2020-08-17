package com.kimochisoft.practicalcaseone.core.di

import com.kimochisoft.data.SessionManager
import com.kimochisoft.practicalcaseone.core.BaseApplication
import com.kimochisoft.practicalcaseone.core.di.modules.RepositoryModule
import com.kimochisoft.practicalcaseone.core.di.modules.NetworkModule
import com.kimochisoft.practicalcaseone.core.di.viewmodel.ViewModelFactory
import com.kimochisoft.practicalcaseone.core.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuildersModule::class,
    RepositoryModule::class,
    NetworkModule::class,
    ViewModelModule::class
])
@Singleton
interface AppComponent : AndroidInjector<BaseApplication> {
    fun sessionManager(): SessionManager?

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: BaseApplication): Builder

        @BindsInstance
        fun applicationBase(applicationBase: BaseApplication): Builder

        fun build(): AppComponent
    }

    fun factory(): ViewModelFactory

}