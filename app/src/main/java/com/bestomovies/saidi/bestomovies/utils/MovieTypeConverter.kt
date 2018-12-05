package com.bestomovies.saidi.bestomovies.utils

import androidx.room.TypeConverter
import com.bestomovies.saidi.bestomovies.data.db.entity.MovieType

class MovieTypeConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun toMovieType(code: Int): MovieType {

            return when (code) {
                1 -> MovieType.POPULAR
                2 -> MovieType.UPCOMING
                3 -> MovieType.TOP_RATED
                else -> {
                    throw IllegalArgumentException("Could not recognize status")
                }
            }
        }

        @TypeConverter
        @JvmStatic
        fun toMovieCode(type: MovieType): Int {
            return type.code
        }
    }
}

