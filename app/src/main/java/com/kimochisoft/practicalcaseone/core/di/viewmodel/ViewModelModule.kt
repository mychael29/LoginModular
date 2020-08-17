package com.kimochisoft.practicalcaseone.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kimochisoft.business.auth.repository.AuthRepository
import com.kimochisoft.business.auth.usecase.LogInUseCase
import com.kimochisoft.business.auth.usecase.SignUpUseCase
import com.kimochisoft.data.SessionManager
import com.kimochisoft.practicalcaseone.ui.auth.logIn.LogInViewModel
import com.kimochisoft.practicalcaseone.ui.auth.signUp.SignUpViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class ViewModelModule {
    @Provides
    fun provideViewModelFactory(providers: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory =
        ViewModelFactory(providers)

    @IntoMap
    @Provides
    @ViewModelKey(LogInViewModel::class)
    fun logInViewModel(repository: AuthRepository): ViewModel = LogInViewModel(LogInUseCase(repository), SessionManager())

    @IntoMap
    @Provides
    @ViewModelKey(SignUpViewModel::class)
    fun signUpViewModel(repository: AuthRepository): ViewModel = SignUpViewModel(SignUpUseCase(repository))
}