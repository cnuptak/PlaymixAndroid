package com.example.playmixandroid

import android.util.Log
import androidx.lifecycle.ViewModel

class PlaymixViewModel : ViewModel() {
    init {
        Log.i("PlaymixViewModel", "PlaymixViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("PlaymixViewModel", "PlaymixViewModel destroyed!")
    }
}

