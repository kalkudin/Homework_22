package com.example.homework_22.domain.usecase

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.Post
import com.example.homework_22.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke(id : Int): Flow<Resource<Post>> {
        return postRepository.getPostDetails(id = id)
    }
}