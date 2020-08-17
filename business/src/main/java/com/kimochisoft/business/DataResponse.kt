package com.kimochisoft.business

sealed class DataResponse<out T : Any> {
    data class Success<T : Any>(val data: T?) : DataResponse<T>()
    data class NetworkError(val code: String, val message: String) : DataResponse<Nothing>()
    data class TimeOutServerError(val code: String, val message: String) : DataResponse<Nothing>()
    data class ExceptionError(val code: String, val message: String) : DataResponse<Nothing>()
    data class ServerError(val code: String, val message: String) : DataResponse<Nothing>()
}