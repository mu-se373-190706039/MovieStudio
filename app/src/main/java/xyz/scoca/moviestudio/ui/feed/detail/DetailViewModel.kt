package xyz.scoca.moviestudio.ui.feed.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.scoca.moviestudio.data.local.entity.MovieDatabase
import xyz.scoca.moviestudio.model.saved.SavedMovieData
import xyz.scoca.moviestudio.repository.SavedMovieRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : SavedMovieRepository

    init {
        val savedMovieDao = MovieDatabase.getDatabase(application).savedMovieDao()
        repository = SavedMovieRepository(savedMovieDao)
    }

    fun addMovie(movie : SavedMovieData){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMovie(movie)
        }
    }
}