package com.kimochisoft.business.auth.usecase

import com.kimochisoft.business.auth.repository.AuthRepository

class LogOutUseCase(private val repository: AuthRepository) {
    fun invoke() = repository.logOut()
}