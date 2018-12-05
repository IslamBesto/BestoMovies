package com.bestomovies.saidi.bestomovies.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
        @PrimaryKey(autoGenerate = false)
        var id: Int = 0,
        var adult: Boolean = false,
        @SerializedName("backdrop_path")
        var backdropPath: String = "",
        @SerializedName("original_language")
        var originalLanguage: String = "",
        @SerializedName("original_title")
        var originalTitle: String = "",
        var overview: String = "",
        var popularity: Double = 0.0,
        @SerializedName("poster_path")
        var posterPath: String = "",
        @SerializedName("release_date")
        var releaseDate: String = "",
        var title: String = "",
        var video: Boolean = false,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        var type: MovieType = MovieType.POPULAR
)