package com.kimochisoft.business.auth.entity

import com.kimochisoft.business.user.entity.User

data class SignUpRequest(
    val password: String,
    val avatar: String,
    val blUserLocale: String,
    val nickname: String,
    val displayName: String
)

data class LogInRequest(
    val login: String,
    val password: String
)

data class ServerResponse<T>(val code: String?, val message: String?, val data: T?)
data class ErrorResponse(val message: String)
data class VerificationResponse(val code: String, val message: String)


typealias LoginResponse = ServerResponse<User>
