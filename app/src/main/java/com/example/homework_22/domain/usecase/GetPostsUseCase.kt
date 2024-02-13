package com.example.homework_22.domain.usecase

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.Post
import com.example.homework_22.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke() : Flow<Resource<List<Post>>> {
        return postRepository.getPosts()
    }
}