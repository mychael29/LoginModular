package com.kimochisoft.business.auth.usecase

import com.kimochisoft.business.auth.repository.AuthRepository

class LogInUseCase(private val repository: AuthRepository) {
    fun invoke(nickname: String, password: String) = repository.logIn(nickname, password)
}