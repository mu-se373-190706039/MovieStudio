package xyz.scoca.moviestudio.ui.saved

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout
import xyz.scoca.moviestudio.R
import xyz.scoca.moviestudio.databinding.FragmentSavedBinding
import xyz.scoca.moviestudio.model.saved.SavedMovieData
import xyz.scoca.moviestudio.ui.saved.adapter.SavedMovieAdapter
import xyz.scoca.moviestudio.utils.OnItemClickListener
import xyz.scoca.moviestudio.utils.extensions.snack

class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    private lateinit var viewModel : SavedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SavedViewModel::class.java]
        setRecyclerViewSnapHelper()
        setBackgroundAnimation()

        observeData()
        return binding.root
    }

    private fun observeData(){
        viewModel.readAllMovies.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()){
                binding.tvEmpty.visibility = View.VISIBLE
            }else{
                binding.tvEmpty.visibility = View.GONE
            }
            setRecyclerViewAdapter(it)
        })
    }
    private fun setRecyclerViewAdapter(savedList : List<SavedMovieData>){
        val linearLayoutManager = ZoomRecyclerLayout(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.rvZoomSaved.layoutManager = linearLayoutManager
        binding.rvZoomSaved.adapter = SavedMovieAdapter(requireContext(),savedList,object : OnItemClickListener{
            override fun onClick(position: Int) {
                showDialog(
                    "Are you sure you want to delete this movie?",
                    object : OnItemClickListener{
                        override fun onClick(position: Int) {
                            deleteMovie(savedList[position])
                            snack(requireView()," ${savedList[position].movieName} Deleted Successfully.")
                        }
                    }
                )
            }
        })
    }

    private fun setBackgroundAnimation() {
        val animDrawable = binding.rootLayout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()
    }

    private fun setRecyclerViewSnapHelper() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvZoomSaved)
        binding.rvZoomSaved.isNestedScrollingEnabled = false
    }
    private fun deleteMovie(movie : SavedMovieData){
        viewModel.deleteMovie(movie)
    }

    private fun showDialog(
        message: String,
        onClickListener: OnItemClickListener
    ) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_alert_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setGravity(Gravity.CENTER)

        val tvMessage = dialog.findViewById<AppCompatTextView>(R.id.tvMessage)
        val btnDelete = dialog.findViewById<AppCompatButton>(R.id.btnDelete)
        tvMessage.text = message
        btnDelete.setOnClickListener {
            dialog.dismiss()
            onClickListener.onClick(0)
        }
        dialog.window?.let {
            it.attributes.windowAnimations = R.style.SlidingDialogAnimation
        }
        dialog.show()
    }
}