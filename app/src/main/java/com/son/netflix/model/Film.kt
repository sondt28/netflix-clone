package com.son.netflix.model

import java.io.Serializable

data class Film(
    var image: Int,
    var name: String,
    var fileFilm: Int? = null
) : Serializable
