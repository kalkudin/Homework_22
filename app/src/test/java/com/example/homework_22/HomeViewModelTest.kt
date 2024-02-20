package com.example.homework_22

import com.example.homework_22.data.common.ErrorType
import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.Post
import com.example.homework_22.domain.model.Story
import com.example.homework_22.domain.usecase.GetPostsUseCase
import com.example.homework_22.domain.usecase.GetStoriesUseCase
import com.example.homework_22.presentation.event.HomeEvent
import com.example.homework_22.presentation.home.HomeViewModel
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @Mock
    private lateinit var getStoriesUseCase: GetStoriesUseCase

    @Mock
    private lateinit var getPostsUseCase: GetPostsUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(getStoriesUseCase, getPostsUseCase)
    }

    @Test
    fun `getStories emits correct states`() = runTest {
        val stories = listOf(
            Story(id = 1, cover = "jemali", title = "jemali^2"),
            Story(id = 2, cover = "jemali", title = "jemali^2"),
            Story(id = 3, cover = "jemali", title = "jemali^2")
        )
        `when`(getStoriesUseCase()).thenReturn(flowOf(Resource.Success(stories)))

        viewModel.onEvent(HomeEvent.GetStories)


    }

    @Test
    fun `getPosts emits correct states`() = runTest {
        val posts = listOf(
            Post(
                id = 1,
                images = listOf("https://example.com/image1.jpg", "https://example.com/image2.jpg"),
                title = "A Day in the Life",
                comments = 42,
                likes = 128,
                firstName = "Alex",
                lastName = "Johnson",
                profile = "https://example.com/profile/alex.jpg",
                postDate = "2023-01-01"
            ),
            Post(
                id = 2,
                images = listOf("https://example.com/image3.jpg", "https://example.com/image4.jpg"),
                title = "Exploring the Mountains",
                comments = 35,
                likes = 97,
                firstName = "Casey",
                lastName = "Smith",
                profile = "https://example.com/profile/casey.jpg",
                postDate = "2023-01-02"
            )
        )
        `when`(getPostsUseCase()).thenReturn(flowOf(Resource.Success(posts)))

        viewModel.onEvent(HomeEvent.GetPosts)
    }

    @Test
    fun `getPosts emits error state on network error`() = runTest {
        whenever(getPostsUseCase()).thenReturn(
            flowOf(Resource.Error(statusCode = 0, errorType = ErrorType.NetworkError))
        )

        viewModel.onEvent(HomeEvent.GetPosts)

        val state = viewModel.homeState.value
        state.error?.let { assertTrue(it.isNotEmpty()) }
        assertFalse(state.isLoading)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}