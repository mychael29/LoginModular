package com.kimochisoft.practicalcaseone.common

import com.kimochisoft.data.Constant
import com.kimochisoft.data.local.SharedPreferenceDataSource

object UserSingleton {
    private var repository: SharedPreferenceDataSource? = null
    fun init(repository: SharedPreferenceDataSource) {
        this.repository = repository
    }

    fun getUid(): String = repository?.getString(Constant.PREF_USER_UID, "").toString()
    fun setUid(value: String?) = repository?.putString(Constant.PREF_USER_UID, value)
    fun getEmail(): String = repository?.getString(Constant.PREF_USER_EMAIL, "").toString()
    fun setEmail(value: String?) = repository?.putString(Constant.PREF_USER_EMAIL, value)
}