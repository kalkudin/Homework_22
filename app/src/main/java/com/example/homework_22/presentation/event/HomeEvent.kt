package com.example.homework_22.presentation.event

sealed class HomeEvent {
    data object GetStories : HomeEvent()
    data object GetPosts : HomeEvent()

    data class NavigateToDetails(val id : Int) : HomeEvent()
}