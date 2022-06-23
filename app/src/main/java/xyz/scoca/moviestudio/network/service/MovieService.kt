package xyz.scoca.moviestudio.network.service

import retrofit2.Call
import retrofit2.http.GET
import xyz.scoca.moviestudio.model.popular.PopularMovie
import xyz.scoca.moviestudio.model.toprated.TopRatedMovie

interface MovieService {

    @GET("top_rated?api_key=1e980008289be51ae91f4e99a8d51fd5&language=en-US&page=1")
    fun getTopRatedMovie() : Call<TopRatedMovie>

    @GET("popular?api_key=1e980008289be51ae91f4e99a8d51fd5&language=en-US&page=1")
    fun getPopularMovie() : Call<PopularMovie>
}