package xyz.scoca.moviestudio.repository

import androidx.lifecycle.LiveData
import xyz.scoca.moviestudio.data.local.entity.SavedMovieDao
import xyz.scoca.moviestudio.model.Movie
import xyz.scoca.moviestudio.model.saved.SavedMovieData

class SavedMovieRepository(
    private val savedMovieDao : SavedMovieDao
){
    val readAllMovies : LiveData<List<SavedMovieData>> = savedMovieDao.readAllMovies()

    suspend fun addMovie(movie : SavedMovieData){
        savedMovieDao.addMovie(movie)
    }
    suspend fun deleteMovie(movie : SavedMovieData){
        savedMovieDao.deleteMovie(movie)
    }

}