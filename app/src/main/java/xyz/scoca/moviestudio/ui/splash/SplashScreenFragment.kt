package xyz.scoca.moviestudio.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import xyz.scoca.moviestudio.R
import xyz.scoca.moviestudio.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private lateinit var binding : FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater,container,false)

        val textViewAnimation : Animation = AnimationUtils.loadAnimation(requireContext(),R.anim.textview_animation)
        binding.tvAppName.animation = textViewAnimation
        binding.splashAnimation.speed = 1.5f
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_feedFragment)
        },3000)
        return binding.root
    }
}