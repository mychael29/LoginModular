package com.kimochisoft.business.auth.usecase

import com.kimochisoft.business.auth.repository.AuthRepository
import com.kimochisoft.business.user.entity.User

class SignUpUseCase(private val repository: AuthRepository) {
    fun invoke(user: User, password: String) = repository.logUp(user, password)
}