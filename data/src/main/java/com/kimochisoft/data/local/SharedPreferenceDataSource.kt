package com.kimochisoft.data.local

import android.content.Context
import android.content.SharedPreferences
import com.kimochisoft.data.Constant

class SharedPreferenceDataSource (private val application: Context) : PreferenceDataSource {
    private val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences(Constant.PREF_NAME, Constant.PRIVATE_MODE)
    }

    fun getString(key: String, default: String): String {
        return sharedPreferences.get(key, default)
    }

    fun putString(key: String, value: String?) {
        sharedPreferences.put(key, value)
    }
}

inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when (T::class) {
        Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
        Float::class -> return this.getFloat(key, defaultValue as Float) as T
        Int::class -> return this.getInt(key, defaultValue as Int) as T
        Long::class -> return this.getLong(key, defaultValue as Long) as T
        String::class -> return this.getString(key, defaultValue as String) as T
        else -> {
            if (defaultValue is Set<*>) {
                return this.getStringSet(key, defaultValue as Set<String>) as T
            }
        }
    }

    return defaultValue
}

inline fun <reified T> SharedPreferences.put(key: String, value: T): T {
    val editor = this.edit()

    when (T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
        else -> {
            if (value is Set<*>) {
                editor.putStringSet(key, value as Set<String>)
            }
        }
    }

    editor.commit()
    return value
}

fun String.validate():Boolean{
    if(this.trim().isEmpty()){
        return false
    }
    return true
}