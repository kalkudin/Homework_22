package com.example.homework_22.data.repository

import com.example.homework_22.data.common.HandleResponse
import com.example.homework_22.data.common.Resource
import com.example.homework_22.data.remote.mapper.mapResource
import com.example.homework_22.data.remote.mapper.toDomain
import com.example.homework_22.data.remote.service.StoryService
import com.example.homework_22.domain.model.Story
import com.example.homework_22.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val storyService: StoryService,
    private val handleResponse: HandleResponse
) : StoryRepository {
    override suspend fun getStories(): Flow<Resource<List<Story>>> {
        return handleResponse.handleApiCall { storyService.getStory() }
            .mapResource { storyDtoList -> storyDtoList.map { it.toDomain() } }
    }
}