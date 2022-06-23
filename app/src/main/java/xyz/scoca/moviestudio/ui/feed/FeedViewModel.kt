package xyz.scoca.moviestudio.ui.feed

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.scoca.moviestudio.model.popular.PopularMovie
import xyz.scoca.moviestudio.model.popular.PopularMovieResult
import xyz.scoca.moviestudio.model.toprated.TopRatedMovie
import xyz.scoca.moviestudio.model.toprated.TopRatedResult
import xyz.scoca.moviestudio.network.NetworkHelper

class FeedViewModel : ViewModel() {

    val topRatedMovies = MutableLiveData<List<TopRatedResult>?>()
    val popularMovies = MutableLiveData<List<PopularMovieResult>?>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    fun getTopRatedMovies() {
        movieLoading.value = true
        NetworkHelper().movieService?.getTopRatedMovie()
            ?.enqueue(object : Callback<TopRatedMovie>{
                override fun onResponse(
                    call: Call<TopRatedMovie>,
                    response: Response<TopRatedMovie>
                ) {
                    movieError.value = false
                    movieLoading.value = false
                    topRatedMovies.value = response.body()?.results
                }

                override fun onFailure(call: Call<TopRatedMovie>, t: Throwable) {
                    movieError.value = true
                    movieLoading.value = false
                }
            })
    }

    fun getPopularMovies(){
        movieLoading.value = true
        NetworkHelper().movieService?.getPopularMovie()
            ?.enqueue(object : Callback<PopularMovie>{
                override fun onResponse(
                    call: Call<PopularMovie>,
                    response: Response<PopularMovie>
                ) {
                    movieError.value = false
                    movieLoading.value = false
                    popularMovies.value = response.body()?.results
                }

                override fun onFailure(call: Call<PopularMovie>, t: Throwable) {
                    movieError.value = true
                    movieLoading.value = false
                }
            })
    }
}

