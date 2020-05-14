package com.example.playmixandroid

import androidx.appcompat.app.AppCompatActivity
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.playmixandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //mediaPlayer = MediaPlayer.create(this,R.raw.april)
        //mediaPlayer?.setOnPreparedListener{println("Ready to go")}

        //val playButton: Button = findViewById(R.id.play_button)
        //playButton.setOnClickListener{
            //mediaPlayer?.start()
            //Toast.makeText(this,"Playing the file", Toast.LENGTH_SHORT).show()
        }
    }
