package com.example.homework_22.data.remote.mapper

import com.example.homework_22.data.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R> Flow<Resource<T>>.mapResource(transform: (T) -> R): Flow<Resource<R>> = this.map { resource ->
    when (resource) {
        is Resource.Success -> Resource.Success(transform(resource.data))
        is Resource.Error -> Resource.Error(resource.statusCode, resource.errorType)
        is Resource.Loading -> Resource.Loading
    }
}