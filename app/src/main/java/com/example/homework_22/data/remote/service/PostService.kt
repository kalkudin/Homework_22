package com.example.homework_22.data.remote.service

import com.example.homework_22.data.remote.model.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
    @GET("/v3/690f6baf-a3a8-4b0e-a4b0-471eb06d53b5")
    suspend fun getPosts(): Response<List<PostDto>>
}