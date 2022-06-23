package xyz.scoca.moviestudio.model.saved

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class SavedMovieData(
    @PrimaryKey(autoGenerate = true)
    val id :Int = 0,
    val posterPath : String,
    val movieName : String,
    val voteAverage : Float
)
