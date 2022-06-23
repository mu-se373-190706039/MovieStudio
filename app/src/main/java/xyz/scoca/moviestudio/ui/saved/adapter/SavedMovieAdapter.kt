package xyz.scoca.moviestudio.ui.saved.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import xyz.scoca.moviestudio.databinding.MovieSavedItemBinding
import xyz.scoca.moviestudio.model.Movie
import xyz.scoca.moviestudio.model.saved.SavedMovieData
import xyz.scoca.moviestudio.utils.Const.IMAGE_BASE
import xyz.scoca.moviestudio.utils.OnItemClickListener
import xyz.scoca.moviestudio.utils.extensions.loadImage

class SavedMovieAdapter(
    private val context: Context,
    private val savedList: List<SavedMovieData>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SavedMovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: MovieSavedItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {
            val itemPosition = savedList[adapterPosition]
                itemBinding.ivMovieImage.loadImage(IMAGE_BASE +itemPosition.posterPath)
                itemBinding.rbAverageRate.rating = itemPosition.voteAverage
                itemBinding.tvMovieName.text = itemPosition.movieName

            itemBinding.cardView.setOnClickListener {
                onItemClickListener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            MovieSavedItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = savedList.size

}