package xyz.scoca.moviestudio.data.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.scoca.moviestudio.model.Movie
import xyz.scoca.moviestudio.model.saved.SavedMovieData

@Database(entities = [SavedMovieData::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun savedMovieDao() : SavedMovieDao

    companion object{
        @Volatile // rights to this field are immediately made visible to other threads
        private var INSTANCE : MovieDatabase? = null

        fun getDatabase(context : Context) : MovieDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                        "movieDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}