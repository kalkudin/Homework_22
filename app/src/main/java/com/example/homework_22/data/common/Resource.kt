package com.example.homework_22.data.common

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val statusCode: Int, val errorType: ErrorType) : Resource<Nothing>()
}
