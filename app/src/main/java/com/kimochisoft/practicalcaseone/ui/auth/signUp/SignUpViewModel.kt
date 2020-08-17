package com.kimochisoft.practicalcaseone.ui.auth.signUp

import androidx.lifecycle.ViewModel
import com.kimochisoft.business.auth.usecase.SignUpUseCase
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

}