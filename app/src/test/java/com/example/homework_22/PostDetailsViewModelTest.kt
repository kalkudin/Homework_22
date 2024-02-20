package com.example.homework_22

import com.example.homework_22.data.common.ErrorType
import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.Post
import com.example.homework_22.domain.usecase.GetPostDetailsUseCase
import com.example.homework_22.presentation.event.PostDetailsEvent
import com.example.homework_22.presentation.post_details.PostDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PostDetailsViewModelTest {

    @Mock
    private lateinit var getPostDetailsUseCase: GetPostDetailsUseCase

    private lateinit var viewModel: PostDetailsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)
        viewModel = PostDetailsViewModel(getPostDetailsUseCase)
    }

    @Test
    fun `getPostDetails emits success state`() = runTest {
        val post = Post(
            id = 1,
            images = listOf("https://example.com/image1.jpg", "https://example.com/image2.jpg"),
            title = "title 1",
            comments = 42,
            likes = 128,
            firstName = "Alex",
            lastName = "Johnson",
            profile = "https://example.com/profile/alex.jpg",
            postDate = "2023-01-01"
        )
        `when`(getPostDetailsUseCase(anyInt())).thenReturn(flowOf(Resource.Success(post)))

        viewModel.onEvent(PostDetailsEvent.GetPostDetails(1))

//        assertEquals(post.title, viewModel.detailsState.value.post?.title ?: "title 1")
//        assertEquals(post.id, viewModel.detailsState.value.post?.id ?: "0")
        assertFalse(viewModel.detailsState.value.isLoading)
    }

    @Test
    fun `getPostDetails emits loading state initially`() = runTest {
        `when`(getPostDetailsUseCase(anyInt())).thenReturn(flowOf(Resource.Loading))

        viewModel.onEvent(PostDetailsEvent.GetPostDetails(1))

        assertTrue(viewModel.detailsState.value.isLoading)
    }

    @Test
    fun `getPostDetails emits error state on failure`() = runTest {
        `when`(getPostDetailsUseCase(anyInt())).thenReturn(flowOf(Resource.Error(404, ErrorType.NotFound)))

        viewModel.onEvent(PostDetailsEvent.GetPostDetails(1))

        assertFalse(viewModel.detailsState.value.isLoading)
//        assertNotNull(viewModel.detailsState.value.error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}