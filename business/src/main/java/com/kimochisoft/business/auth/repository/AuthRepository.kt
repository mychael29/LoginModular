package com.kimochisoft.business.auth.repository

import com.kimochisoft.business.auth.entity.LoginResponse
import com.kimochisoft.business.auth.entity.VerificationResponse
import com.kimochisoft.business.user.entity.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface AuthRepository {
    fun logIn(nickname: String, password: String): Single<User>
    fun logUp(user: User, password: String): Single<LoginResponse>
    fun logOut(): Single<VerificationResponse>
}