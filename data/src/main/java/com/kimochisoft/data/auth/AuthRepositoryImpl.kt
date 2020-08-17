package com.kimochisoft.data.auth

import com.kimochisoft.business.auth.entity.LogInRequest
import com.kimochisoft.business.auth.entity.LoginResponse
import com.kimochisoft.business.auth.entity.SignUpRequest
import com.kimochisoft.business.auth.entity.VerificationResponse
import com.kimochisoft.business.auth.repository.AuthRepository
import com.kimochisoft.business.user.entity.User
import com.kimochisoft.data.api.AuthApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi): AuthRepository {

    override fun logIn(nickname: String, password: String): Single<User> {
        return authApi.getInitSession(LogInRequest(nickname, password))
    }

    override fun logUp(user: User, password: String): Single<LoginResponse> {
        return authApi.getRegister(SignUpRequest(password, user.avatar, "", user.nickname, ""))
    }

    override fun logOut(): Single<VerificationResponse> {
        return authApi.getEndSession()
    }
}