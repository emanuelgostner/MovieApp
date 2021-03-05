package com.example.movieapp

data class Movie(val title: String = "title_placeholder",
                 val description: String = "description_placeholder",
                 val rating: Float = 4.5f,
                 val genres: String? = "genre1, genre2",
                 val creators: String = "creator1, creator2",
                 val actors: String = "actor1, actor2")