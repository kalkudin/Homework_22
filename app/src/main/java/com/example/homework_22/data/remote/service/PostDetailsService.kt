package com.example.homework_22.data.remote.service

import com.example.homework_22.data.remote.model.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostDetailsService {
    @GET("v3/d02b76bb-095d-45fa-90e1-dc4733d1f247")
    suspend fun getPostById(@Query("id") id: Int): Response<PostDto>
}