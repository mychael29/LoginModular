package com.kimochisoft.practicalcaseone.core.di.modules.auth

import androidx.lifecycle.ViewModel
import com.kimochisoft.practicalcaseone.core.di.viewmodel.ViewModelKey
import com.kimochisoft.practicalcaseone.ui.auth.logIn.LogInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    abstract fun bindAuthViewModel(viewModel: LogInViewModel?): ViewModel?
}