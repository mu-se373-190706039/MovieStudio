package xyz.scoca.moviestudio.data.local.entity

import androidx.lifecycle.LiveData
import androidx.room.*
import xyz.scoca.moviestudio.model.Movie
import xyz.scoca.moviestudio.model.saved.SavedMovieData
@Dao
interface SavedMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //If there is a new same data , ignore that.
    suspend fun addMovie(movie : SavedMovieData)

    @Query("SELECT *FROM movie_table ORDER BY id ASC")
    fun readAllMovies() : LiveData<List<SavedMovieData>>

    @Delete
    suspend fun deleteMovie(movie : SavedMovieData)
}