package xyz.scoca.moviestudio.model.popular


import com.google.gson.annotations.SerializedName

data class PopularMovie(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<PopularMovieResult>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)