package com.example.homework_22.domain.repository

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    suspend fun getStories() : Flow<Resource<List<Story>>>
}