package com.kimochisoft.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.kimochisoft.business.user.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateToken(source: LiveData<AuthResource<User>>) {
        cachedUser.setValue(AuthResource.loading(null))
        cachedUser.addSource(source, Observer { userAuthResource ->
            cachedUser.value = userAuthResource
            cachedUser.removeSource(source)
            if (userAuthResource.status == AuthResource.Companion.AuthStatus.ERROR) {
                cachedUser.value = AuthResource.logout()
            }
        })
    }

    fun logOut() {
        cachedUser.value = AuthResource.logout()
    }


    fun getAuthUser(): LiveData<AuthResource<User>> {
        return cachedUser
    }
}