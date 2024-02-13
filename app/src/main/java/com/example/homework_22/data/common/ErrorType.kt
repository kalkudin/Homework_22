package com.example.homework_22.data.common

sealed class ErrorType {
    data object NetworkError : ErrorType()
    data object AuthenticationError : ErrorType()
    data object NotFound : ErrorType()
    data object AccessDenied : ErrorType()
    data object ServiceUnavailable : ErrorType()
    data class UnknownError(val errorMessage: String) : ErrorType()
}