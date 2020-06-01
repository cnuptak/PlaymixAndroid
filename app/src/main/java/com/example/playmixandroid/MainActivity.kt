package com.example.playmixandroid

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.playmixandroid.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_title.*
import timber.log.Timber

const val KEY_DURATION = "key_duration"
const val KEY_TIMEPOSITION = "key_timeposition"

class MainActivity : AppCompatActivity() {
    private var mediaPlayer : MediaPlayer? = null
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var trackTimer: TrackTimer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()

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
                if (mediaPlayer?.isPlaying()!!) {
                    mediaPlayer?.pause();
                    playButton.setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp)
                    Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
                } else {
                    mediaPlayer?.start()
                    initializeSeekBar()
                    playButton.setBackgroundResource(R.drawable.ic_pause_circle_outline_black_24dp)
                    Toast.makeText(this, "Playing the file", Toast.LENGTH_SHORT).show()
                }
            }
            val stopButton: Button = findViewById(R.id.stop_button)
            stopButton.setOnClickListener{
                seek_bar.setProgress(0)
                mediaPlayer?.stop()
                Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show()
            }
        }

        trackTimer = TrackTimer(this.lifecycle)

        seek_bar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        if (savedInstanceState != null){
            savedInstanceState.getInt(KEY_DURATION)
            savedInstanceState.getInt(KEY_TIMEPOSITION)
        }
    }

    fun millisecondsToTime(milliseconds: Int): String {
        val minutes = ((milliseconds/1000)%3600)/60
        val seconds = (milliseconds/1000) % 60
        return String.format("%02d:%02d",minutes,seconds)
    }

    private fun initializeSeekBar() {
        seek_bar.max = mediaPlayer?.seconds!!
        runnable = Runnable{
            seek_bar.progress = mediaPlayer?.currentSeconds!!
            current_time.text = millisecondsToTime(mediaPlayer?.currentSeconds!!)

            val timepos = mediaPlayer?.seconds!!
            duration.text = millisecondsToTime(timepos)
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    val MediaPlayer.seconds:Int
        get() {
            return this.duration
        }

    val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition
        }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_DURATION, mediaPlayer?.currentSeconds!!)
        outState.putInt(KEY_TIMEPOSITION, mediaPlayer?.seconds!!)
        Timber.i("onSaveInstanceState called")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
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