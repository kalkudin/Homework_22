package com.example.homework_22.domain.usecase

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.Story
import com.example.homework_22.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(private val storyRepository: StoryRepository) {
    suspend operator fun invoke() : Flow<Resource<List<Story>>> {
        return storyRepository.getStories()
    }
}