package com.kimochisoft.data.api

import com.kimochisoft.business.auth.entity.LogInRequest
import com.kimochisoft.business.auth.entity.LoginResponse
import com.kimochisoft.business.auth.entity.SignUpRequest
import com.kimochisoft.business.auth.entity.VerificationResponse
import com.kimochisoft.business.user.entity.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @POST("/users/register")
    fun getRegister(@Body body: SignUpRequest): Single<LoginResponse>

    @POST("users/login")
    fun getInitSession(@Body body: LogInRequest): Single<User>

    @POST("/users/logout")
    fun getEndSession(): Single<VerificationResponse>
}