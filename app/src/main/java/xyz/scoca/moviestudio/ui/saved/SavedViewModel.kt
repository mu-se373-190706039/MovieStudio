package xyz.scoca.moviestudio.ui.saved

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.scoca.moviestudio.data.local.entity.MovieDatabase
import xyz.scoca.moviestudio.model.Movie
import xyz.scoca.moviestudio.model.saved.SavedMovieData
import xyz.scoca.moviestudio.repository.SavedMovieRepository

class SavedViewModel(application: Application) : AndroidViewModel(application) {
    val readAllMovies : LiveData<List<SavedMovieData>>
    private val repository : SavedMovieRepository

    init {
        val savedMovieDao = MovieDatabase.getDatabase(application).savedMovieDao()
        repository = SavedMovieRepository(savedMovieDao)
        readAllMovies = repository.readAllMovies
    }

    fun deleteMovie(movie : SavedMovieData){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMovie(movie)
        }
    }
}