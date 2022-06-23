package xyz.scoca.moviestudio.ui.feed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import xyz.scoca.moviestudio.databinding.TopRatedItemBinding
import xyz.scoca.moviestudio.model.toprated.TopRatedResult
import xyz.scoca.moviestudio.utils.Const.IMAGE_BASE
import xyz.scoca.moviestudio.utils.OnItemClickListener
import xyz.scoca.moviestudio.utils.extensions.loadImage


class TopRatedMovieAdapter(
    private val context: Context,
    private val topRatedMovieList: List<TopRatedResult>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<TopRatedMovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val itemBinding: TopRatedItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {
            val itemPosition = topRatedMovieList[adapterPosition]

            itemBinding.ivTopRatedMovie.loadImage(IMAGE_BASE+itemPosition.posterPath.toString())
            itemBinding.tvMovieName.text = itemPosition.title.toString()

            itemBinding.ivTopRatedMovie.setOnClickListener {
                onItemClickListener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            TopRatedItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = topRatedMovieList.size

}