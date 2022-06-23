package xyz.scoca.moviestudio.ui

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import xyz.scoca.moviestudio.R
import xyz.scoca.moviestudio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBackgroundAnimation()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.feedFragment
                || destination.id == R.id.savedFragment
            ) {
                binding.bottomNav.visibility = View.VISIBLE
            }
            else
                binding.bottomNav.visibility = View.GONE
        }
    }

    private fun setBackgroundAnimation() {
        val animDrawable = binding.bottomNav.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()
    }
}