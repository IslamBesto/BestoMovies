package com.bestomovies.saidi.bestomovies.data.entity

import com.google.gson.annotations.SerializedName

data class TheMovieDbApi(
        val page: Int,
        @SerializedName("results")
        val movies: List<Movie>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)