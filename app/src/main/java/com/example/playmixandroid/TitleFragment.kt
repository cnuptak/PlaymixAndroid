package com.example.playmixandroid

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.playmixandroid.databinding.FragmentTitleBinding
import android.app.Activity
import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_title.*

class TitleFragment : Fragment() {

    private lateinit var viewModel: PlaymixViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )

        Log.i("TitleFragment","Called ViewModel.of!")
        viewModel = ViewModelProvider(this).get(PlaymixViewModel::class.java)

        binding.TracklistButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_tracklistFragment32)
        )

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflowmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,view!!.findNavController())
                ||super.onOptionsItemSelected(item)
    }
}
