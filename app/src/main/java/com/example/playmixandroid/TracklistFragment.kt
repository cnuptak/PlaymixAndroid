package com.example.playmixandroid

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment


class TracklistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_tracklist, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.sharetracklistmenu, menu)
        // check if the activity resolved
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
        //hide menu
        menu?.findItem(R.id.sharemenu)?.setVisible(false)
        }
    }

    private fun getShareIntent() : Intent {
        return ShareCompat.IntentBuilder.from(activity!!).setText(getString(R.string.tracklist))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.sharemenu -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}

