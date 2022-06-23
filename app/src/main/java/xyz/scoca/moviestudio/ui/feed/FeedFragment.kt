package xyz.scoca.moviestudio.ui.feed

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout
import xyz.scoca.moviestudio.databinding.FragmentFeedBinding
import xyz.scoca.moviestudio.model.Movie
import xyz.scoca.moviestudio.model.popular.PopularMovieResult
import xyz.scoca.moviestudio.model.toprated.TopRatedResult
import xyz.scoca.moviestudio.ui.feed.adapter.TopRatedMovieAdapter
import xyz.scoca.moviestudio.ui.feed.adapter.PopularMovieAdapter
import xyz.scoca.moviestudio.utils.OnItemClickListener


class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(requireActivity())[FeedViewModel::class.java]
        setBackgroundAnimation()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTopRatedRecyclerViewSnapHelper()
        setRecentPopularRecyclerViewSnapHelper()

        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { topRatedMovies ->
            topRatedMovies?.let {
                binding.rvTopRated.visibility = View.VISIBLE
                setTopRatedMovieRecyclerViewAdapter(it)
            }
        })

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { popularMovies ->
            popularMovies?.let {
                binding.rvPopular.visibility = View.VISIBLE
                setPopularMovieRecyclerViewAdapter(it)
            }
        })

        viewModel.movieLoading.observe(viewLifecycleOwner, Observer { onLoading ->
            onLoading?.let { status ->
                if (status) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvFeedErrorMessage.visibility = View.GONE

                    binding.rvPopular.visibility = View.GONE
                    binding.rvTopRated.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.GONE

                    binding.rvPopular.visibility = View.VISIBLE
                    binding.rvTopRated.visibility = View.VISIBLE
                }
            }
        })

        viewModel.movieError.observe(viewLifecycleOwner, Observer { onError ->
            onError?.let { status ->
                if (status) {
                    binding.progressBar.visibility = View.GONE
                    binding.tvFeedErrorMessage.visibility = View.VISIBLE

                    binding.rvPopular.visibility = View.GONE
                    binding.rvTopRated.visibility = View.GONE
                } else {
                    binding.tvFeedErrorMessage.visibility = View.GONE
                    binding.rvPopular.visibility = View.VISIBLE
                    binding.rvTopRated.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setBackgroundAnimation() {
        val animDrawable = binding.rootLayout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()
    }

    private fun setTopRatedMovieRecyclerViewAdapter(topRatedMovies: List<TopRatedResult>) {
        val linearLayoutManager = ZoomRecyclerLayout(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvTopRated.layoutManager = linearLayoutManager

        binding.rvTopRated.adapter =
            TopRatedMovieAdapter(requireContext(), topRatedMovies, object : OnItemClickListener {
                override fun onClick(position: Int) {
                    val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(Movie(topRatedMovies[position], null))
                    findNavController().navigate(action)
                }
            })
    }

    private fun setPopularMovieRecyclerViewAdapter(popularMovies: List<PopularMovieResult>) {
        val linearLayoutManager = ZoomRecyclerLayout(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvPopular.layoutManager = linearLayoutManager

        binding.rvPopular.adapter =
            PopularMovieAdapter(requireContext(), popularMovies, object : OnItemClickListener {
                override fun onClick(position: Int) {
                    val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(Movie(null, popularMovies[position]))
                    findNavController().navigate(action)
                }
            })
    }

    private fun setTopRatedRecyclerViewSnapHelper() {
        val snapHelper = LinearSnapHelper()

        snapHelper.attachToRecyclerView(binding.rvTopRated)
        binding.rvTopRated.isNestedScrollingEnabled = false
    }

    private fun setRecentPopularRecyclerViewSnapHelper() {
        val snapHelper = LinearSnapHelper()

        snapHelper.attachToRecyclerView(binding.rvPopular)
        binding.rvPopular.isNestedScrollingEnabled = false
    }
}