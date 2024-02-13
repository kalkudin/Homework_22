package com.example.homework_22.presentation.mapper

import com.example.homework_22.data.common.ErrorType

fun mapErrorToMessage(errorType: ErrorType): String {
    return when (errorType) {
        is ErrorType.NetworkError -> "Network error"
        is ErrorType.AuthenticationError -> "Authentication failed"
        is ErrorType.NotFound -> "Items not found"
        is ErrorType.AccessDenied -> "Access denied"
        is ErrorType.ServiceUnavailable -> "Service unavailable"
        is ErrorType.UnknownError -> "Unknown error: ${errorType.errorMessage}"
    }
}