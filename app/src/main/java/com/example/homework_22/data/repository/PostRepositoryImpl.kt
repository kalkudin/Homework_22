package com.example.homework_22.data.repository

import com.example.homework_22.data.common.HandleResponse
import com.example.homework_22.data.common.Resource
import com.example.homework_22.data.remote.mapper.mapResource
import com.example.homework_22.data.remote.mapper.toDomain
import com.example.homework_22.data.remote.service.PostService
import com.example.homework_22.domain.model.Post
import com.example.homework_22.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService,
    private val handleResponse: HandleResponse
) : PostRepository {

    override suspend fun getPosts(): Flow<Resource<List<Post>>> {
        return handleResponse.handleApiCall { postService.getPosts() }
            .mapResource { postDtoList -> postDtoList.map { it.toDomain() } }
    }
}