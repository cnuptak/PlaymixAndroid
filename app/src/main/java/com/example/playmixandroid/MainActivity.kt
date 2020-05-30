package com.example.playmixandroid

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.playmixandroid.databinding.ActivityMainBinding
import timber.log.Timber
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var mediaPlayer : MediaPlayer? = null
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var trackTimer: TrackTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.april)


        mediaPlayer?.setOnPreparedListener {
            println("Ready to go")
            val playButton: Button = findViewById(R.id.play_button)
            playButton.setOnClickListener {
                mediaPlayer?.start()
                Toast.makeText(this, "Playing the file", Toast.LENGTH_SHORT).show()
            }
        }
        trackTimer = TrackTimer(this.lifecycle)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    //override fun onBackPressed() {
        //super.onBackPressed()
        //finish()
    //}

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }
    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }
}