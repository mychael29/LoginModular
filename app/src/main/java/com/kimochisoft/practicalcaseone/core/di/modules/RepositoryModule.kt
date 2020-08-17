package com.kimochisoft.practicalcaseone.core.di.modules

import com.kimochisoft.business.auth.repository.AuthRepository
import com.kimochisoft.data.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun authRepository(repository: AuthRepositoryImpl): AuthRepository { return repository }
}