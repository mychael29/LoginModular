package com.kimochisoft.practicalcaseone.core.di.modules

import com.kimochisoft.data.api.AuthApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun authApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun retrofitInterface(): Retrofit = Retrofit.Builder()
        .baseUrl("http://api.backendless.com/85138EDF-DD0D-C88A-FF1C-850BA58C0E00/D2A0D665-DCA5-4D70-BB4B-5182090C6E04/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}