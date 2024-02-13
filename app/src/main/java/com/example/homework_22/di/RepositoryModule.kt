package com.example.homework_22.di

import com.example.homework_22.data.common.HandleResponse
import com.example.homework_22.data.remote.service.PostService
import com.example.homework_22.data.remote.service.StoryService
import com.example.homework_22.data.repository.PostRepositoryImpl
import com.example.homework_22.data.repository.StoryRepositoryImpl
import com.example.homework_22.domain.repository.PostRepository
import com.example.homework_22.domain.repository.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePostRepository(postService: PostService, handleResponse: HandleResponse) : PostRepository{
        return PostRepositoryImpl(postService = postService, handleResponse = handleResponse)
    }

    @Singleton
    @Provides
    fun provideStoryRepository(storyService: StoryService, handleResponse: HandleResponse) : StoryRepository {
        return StoryRepositoryImpl(storyService = storyService, handleResponse = handleResponse)
    }
}