package com.kimochisoft.practicalcaseone.ui.auth.logIn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kimochisoft.business.user.entity.User
import com.kimochisoft.data.AuthResource
import com.kimochisoft.practicalcaseone.R
import com.kimochisoft.practicalcaseone.core.di.viewmodel.ViewModelFactory
import com.kimochisoft.practicalcaseone.ui.auth.signUp.SignUpActivity
import com.kimochisoft.practicalcaseone.common.EventObserver
import com.kimochisoft.practicalcaseone.common.UserSingleton
import com.kimochisoft.practicalcaseone.ui.main.MainActivity
import dagger.android.AndroidInjection.inject
import kotlinx.android.synthetic.main.log_in_activity.*
import javax.inject.Inject

class LogInActivity : AppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelFactory

    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_activity)
        inject(this)

        viewModel = ViewModelProvider(this, providerFactory).get(LogInViewModel::class.java)

        setUpViewModelObservers()
        subscribeObservers()
        materialButtonLogin.setOnClickListener {
            val nickname = textInputEditTextNickname.text.toString()
            val password = textInputEditTextPassword.text.toString()
            viewModel.validateForLogIn(nickname, password)
        }
        textViewRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            this.finish()
        }
    }

    private fun subscribeObservers() {
        viewModel.observeAuthState().observe(this, Observer {
            when (it.status) {
                AuthResource.Companion.AuthStatus.LOADING -> {
                    // Loading..
                }
                AuthResource.Companion.AuthStatus.AUTHENTICATED -> {
                    onLoginSuccess(it.data as User)
                }
                AuthResource.Companion.AuthStatus.ERROR -> {
                    showErrorMessage(it.message.toString())
                }
                AuthResource.Companion.AuthStatus.NOT_AUTHENTICATED -> {
                    showErrorMessage(it.message.toString())
                }
            }
        })
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        progressBarLogIn.isVisible = it
    }

    private fun setUpViewModelObservers() {
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, EventObserver(::showErrorMessage))
    }

    private fun showErrorMessage(it: Any) {
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onLoginSuccess(user: User) {
        UserSingleton.setUid(user.objectId)
        UserSingleton.setEmail(user.nickname)
        startActivity(Intent(this, MainActivity::class.java) )
        this.finish()
    }

}