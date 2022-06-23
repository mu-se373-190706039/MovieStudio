package xyz.scoca.moviestudio.ui.feed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.scoca.moviestudio.databinding.RecentPopularItemBinding
import xyz.scoca.moviestudio.model.popular.PopularMovieResult
import xyz.scoca.moviestudio.utils.Const.IMAGE_BASE
import xyz.scoca.moviestudio.utils.OnItemClickListener
import xyz.scoca.moviestudio.utils.extensions.loadImage


class PopularMovieAdapter(
    private val context: Context,
    private val popularMovieList : List<PopularMovieResult>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: RecentPopularItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {
            val itemPosition = popularMovieList[adapterPosition]

            itemBinding.ivRecentMovie.loadImage(IMAGE_BASE+itemPosition.posterPath.toString())

            itemBinding.ivRecentMovie.setOnClickListener {
                onItemClickListener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            RecentPopularItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = popularMovieList.size
}