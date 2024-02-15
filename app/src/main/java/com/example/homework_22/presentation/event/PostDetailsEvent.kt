package com.example.homework_22.presentation.event

sealed class PostDetailsEvent {
    data class GetPostDetails(val id : Int) : PostDetailsEvent()
}