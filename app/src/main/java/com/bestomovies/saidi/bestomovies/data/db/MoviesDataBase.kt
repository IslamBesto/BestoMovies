package com.bestomovies.saidi.bestomovies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bestomovies.saidi.bestomovies.data.db.dao.MovieDao
import com.bestomovies.saidi.bestomovies.data.db.entity.Movie
import com.bestomovies.saidi.bestomovies.utils.MovieTypeConverter

@Database(entities = [Movie::class],
        version = 1,
        exportSchema = false)
@TypeConverters(MovieTypeConverter::class)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun upcomingDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MoviesDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        MoviesDataBase::class.java, "movies.db")
                        .build()
    }

}