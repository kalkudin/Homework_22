package com.example.homework_22.domain.repository

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts() : Flow<Resource<List<Post>>>
}