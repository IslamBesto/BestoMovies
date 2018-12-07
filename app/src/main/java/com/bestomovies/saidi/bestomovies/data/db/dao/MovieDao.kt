package com.bestomovies.saidi.bestomovies.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bestomovies.saidi.bestomovies.data.db.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE type = 1")
    fun getPopularMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE type= 3")
    fun getTopRatedMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE type = 2")
    fun getUpcomingMovies(): LiveData<List<Movie>>
}