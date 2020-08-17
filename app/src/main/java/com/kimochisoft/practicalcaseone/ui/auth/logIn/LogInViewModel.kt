package com.kimochisoft.practicalcaseone.ui.auth.logIn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimochisoft.business.auth.usecase.LogInUseCase
import com.kimochisoft.business.user.entity.User
import com.kimochisoft.data.AuthResource
import com.kimochisoft.data.SessionManager
import com.kimochisoft.data.local.validate
import com.kimochisoft.practicalcaseone.R
import com.kimochisoft.practicalcaseone.common.Event
import com.kimochisoft.practicalcaseone.common.UiContext
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LogInViewModel @Inject constructor(private val logInUseCase: LogInUseCase, private val sessionManager: SessionManager) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val authResource: MutableLiveData<AuthResource<User>> = MutableLiveData()
    private var _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> get() = _isViewLoading
    private var _onMessageError = MutableLiveData<Event<Any>>()
    val onMessageError: LiveData<Event<Any>> get() = _onMessageError

    fun validateForLogIn(email: String, password: String) {
        when {
            !email.validate() -> _onMessageError.postValue(Event(UiContext.getString(R.string.invalidInputEmail)))
            !password.validate() -> _onMessageError.postValue(Event(UiContext.getString(R.string.invalidInputPassword)))
            else -> loadLogIn(email, password)
        }
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

    private fun authenticateToken() {
        _isViewLoading.postValue(false)
        sessionManager.authenticateToken(authResource)
    }

    private fun loadLogIn(nickname: String, password: String) {
        _isViewLoading.postValue(true)
        disposables.add(logInUseCase.invoke(nickname, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.wtf("data", "$it")
                authResource.value = AuthResource.authenticated(it)
                authenticateToken()
            }, {
                Log.wtf("error", "$it")
                authResource.value = AuthResource.error("Error en nickname y/o password ", null)
                authenticateToken()
            }))
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}