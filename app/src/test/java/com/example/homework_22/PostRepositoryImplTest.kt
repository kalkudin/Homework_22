package com.example.homework_22

import com.example.homework_22.data.common.HandleResponse
import com.example.homework_22.data.common.Resource
import com.example.homework_22.data.remote.mapper.toDomain
import com.example.homework_22.data.remote.model.PostDto
import com.example.homework_22.data.remote.model.UserDto
import com.example.homework_22.data.remote.service.PostDetailsService
import com.example.homework_22.data.remote.service.PostService
import com.example.homework_22.data.repository.PostRepositoryImpl
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class PostRepositoryImplTest {

//    @Mock
//    private lateinit var postService: PostService
//
//    @Mock
//    private lateinit var postDetailsService: PostDetailsService
//
//    @Mock
//    private lateinit var handleResponse: HandleResponse
//
//    private lateinit var postRepositoryImpl: PostRepositoryImpl
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//        postRepositoryImpl = PostRepositoryImpl(postService, postDetailsService, handleResponse)
//    }
//
//    @Test
//    fun `getPosts returns success`() = runTest {
//        val userDto = UserDto(firstName = "John", lastName = "Doe", profile = "profileUrl", postDate = "2022-01-01")
//        val mockPosts = listOf(
//            PostDto(
//                id = 1,
//                images = listOf("url"),
//                title = "title",
//                comments = 10,
//                likes = 20,
//                owner = userDto
//            )
//        )
//
//        `when`(handleResponse.handleApiCall(any())).thenReturn(flowOf(Resource.Success(mockPosts.map { it.toDomain() })))
//
//        val result = postRepositoryImpl.getPosts().first()
//
//        assert(result is Resource.Success)
//        assertEquals(1, (result as Resource.Success).data.size)
//        assertEquals("title", result.data[0].title)
//    }
}