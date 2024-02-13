package com.example.homework_22.presentation.mapper

import com.example.homework_22.domain.model.Story
import com.example.homework_22.presentation.model.StoryPresentation

fun Story.toPresentation(): StoryPresentation {
    return StoryPresentation(id = id, cover = cover, title = title)
}