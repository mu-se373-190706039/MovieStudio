package xyz.scoca.moviestudio.ui.feed.detail

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import xyz.scoca.moviestudio.databinding.FragmentDetailBinding
import xyz.scoca.moviestudio.model.saved.SavedMovieData
import xyz.scoca.moviestudio.utils.Const.IMAGE_BASE
import xyz.scoca.moviestudio.utils.extensions.loadImage
import xyz.scoca.moviestudio.utils.extensions.snack


class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private lateinit var viewModel : DetailViewModel
    private lateinit var savedMovieData : SavedMovieData
    private val args by navArgs<DetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        setBackgroundAnimation()
        fetchMovieDetails()

        binding.fbSaveMovie.setOnClickListener {
            saveMovie()
        }
        return binding.root
    }

    private fun fetchMovieDetails(){
        if(args.movieDetail.popularMovie!=null){
            with(args.movieDetail.popularMovie!!){
                binding.ivMovieDetail.loadImage(IMAGE_BASE+this.posterPath.toString())

                binding.tvMovieName.text = this.title.toString()
                binding.tvAverageRate.text = (this.voteAverage!!.toFloat() / 2).toString()
                binding.rbAverageRate.rating = (this.voteAverage.toFloat() / 2)
                binding.tvMovieLanguage.text = this.originalLanguage.toString()
                binding.tvMovieOverview.text = this.overview.toString()
            }
        }else{
            with(args.movieDetail.topRatedMovie!!){
                binding.ivMovieDetail.loadImage(IMAGE_BASE+this.posterPath.toString())

                binding.tvMovieName.text = this.title.toString()
                binding.tvAverageRate.text = (this.voteAverage!!.toFloat() / 2).toString()
                binding.rbAverageRate.rating = (this.voteAverage.toFloat() / 2)
                binding.tvMovieLanguage.text = this.originalLanguage.toString()
                binding.tvMovieOverview.text = this.overview.toString()
            }
        }
    }

    private fun saveMovie(){
        if(args.movieDetail.popularMovie != null){
            val posterPath = args.movieDetail.popularMovie!!.posterPath.toString()
            val movieName = args.movieDetail.popularMovie!!.title.toString()
            val voteAverage = args.movieDetail.popularMovie!!.voteAverage!!.toFloat() / 2

            savedMovieData = SavedMovieData(0,posterPath,movieName,voteAverage)
            viewModel.addMovie(savedMovieData)
            snack(requireView(),"Movie Saved Successfully.")
        }else{
            val posterPath = args.movieDetail.topRatedMovie!!.posterPath.toString()
            val movieName = args.movieDetail.topRatedMovie!!.title.toString()
            val voteAverage = args.movieDetail.topRatedMovie!!.voteAverage!!.toFloat() / 2

            savedMovieData = SavedMovieData(0,posterPath,movieName,voteAverage)
            viewModel.addMovie(savedMovieData)
            snack(requireView(),"Movie Saved Successfully.")
        }
    }

    private fun setBackgroundAnimation() {
        val animDrawable = binding.rootLayout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()
    }
}