package com.genadidharma.lafar.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.genadidharma.lafar.R
import com.genadidharma.lafar.databinding.ActivityMainBinding
import com.genadidharma.lafar.ui.discover.DiscoverFragmentDirections
import com.genadidharma.lafar.ui.profile.ProfileFragmentDirections
import com.genadidharma.lafar.util.contentView
import com.google.android.material.transition.MaterialElevationScale

class MainActivity : AppCompatActivity(),
        Toolbar.OnMenuItemClickListener,
        NavController.OnDestinationChangedListener{

    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)

    private val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                ?.childFragmentManager
                ?.fragments
                ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigationAndFab()
    }

    private fun setupBottomNavigationAndFab() {

        binding.run {
            findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(
                    this@MainActivity
            )
        }

        binding.fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            setOnClickListener {
                navigateToDiscover()
            }
        }

        binding.bottomAppBar.apply {
            setOnMenuItemClickListener(this@MainActivity)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.profileFragment -> {
                val directions = ProfileFragmentDirections.actionGlobalProfileFragment()
                findNavController(R.id.nav_host_fragment).navigate(directions)
            }
        }
        return true
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when(destination.id){
            R.id.homeFragment -> {
                setBottomAppBarForHome(R.menu.botom_app_bar_menu)
            }
            R.id.discoverFragment -> {
                setBottomAppBarForDiscover()
            }
            R.id.profileFragment -> {
                setBottomAppBarForHome(R.menu.botom_app_bar_menu)
            }
        }
    }

    private fun navigateToDiscover() {
        currentNavigationFragment?.apply {
            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.lafar_motion_duration_large).toLong()
            }
        }
        val directions = DiscoverFragmentDirections.actionGlobalDiscoverFragment()
        findNavController(R.id.nav_host_fragment).navigate(directions)
    }

    private fun setBottomAppBarForDiscover(){
        hideBottomAppBar()
    }

    private fun hideBottomAppBar() {
        binding.run {
            bottomAppBar.performHide()
            // Get a handle on the animator that hides the bottom app bar so we can wait to hide
            // the fab and bottom app bar until after it's exit animation finishes.
            bottomAppBar.animate().setListener(object : AnimatorListenerAdapter() {
                var isCanceled = false
                override fun onAnimationEnd(animation: Animator?) {
                    if (isCanceled) return

                    // Hide the BottomAppBar to avoid it showing above the keyboard
                    // when composing a new email.
                    bottomAppBar.visibility = View.GONE
                    fab.visibility = View.INVISIBLE
                }
                override fun onAnimationCancel(animation: Animator?) {
                    isCanceled = true
                }
            })
        }
    }

    private fun setBottomAppBarForHome(@MenuRes menuRes: Int) {
        binding.run {
            fab.setImageState(intArrayOf(-android.R.attr.state_activated), true)
            bottomAppBar.visibility = View.VISIBLE
            bottomAppBar.replaceMenu(menuRes)
            fab.contentDescription = getString(R.string.discover_fab_description)
            bottomAppBarTitle.visibility = View.VISIBLE
            bottomAppBar.performShow()
            fab.show()
        }
    }

}