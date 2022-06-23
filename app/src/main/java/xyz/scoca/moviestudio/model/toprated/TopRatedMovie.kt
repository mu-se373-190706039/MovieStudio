package xyz.scoca.moviestudio.model.toprated


import com.google.gson.annotations.SerializedName

data class TopRatedMovie(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<TopRatedResult>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null

)