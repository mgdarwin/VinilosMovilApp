package com.example.vinilosmovilapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.vinilosmovilapp.R
import com.example.vinilosmovilapp.databinding.TrackNewFragmentBinding
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.models.Track
import com.example.vinilosmovilapp.viewmodels.AlbumDetailViewModel
import com.example.vinilosmovilapp.viewmodels.TrackNewViewModel

class TrackNewFragment : Fragment() {

    private lateinit var currentAlbum: Album
    private var _binding: TrackNewFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var trackNewViewModel: TrackNewViewModel
    private val albumDetailViewModel: AlbumDetailViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.text_add_new_track)

        trackNewViewModel = ViewModelProvider(this, TrackNewViewModel.Factory(activity.application))
            .get(TrackNewViewModel::class.java)

        trackNewViewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        trackNewViewModel.eventNetworkSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) onSubmitSuccess()
        }
    }

    private fun onNetworkError() {
        if (!trackNewViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network error", Toast.LENGTH_LONG).show()
            trackNewViewModel.onNetworkErrorShown()
        }
    }

    private fun onSubmitSuccess() {
        if (!trackNewViewModel.isSuccessShown.value!!) {
            Toast.makeText(activity, "New track saved", Toast.LENGTH_LONG).show()
            trackNewViewModel.onSuccessShown()
            binding.trackNameField.text?.clear()
            binding.trackDurationField.text?.clear()
            binding.trackNameField.requestFocus()
            if (this.currentAlbum != null) {
                albumDetailViewModel.getAlbumDetail(currentAlbum.albumId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TrackNewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: TrackNewFragmentArgs by navArgs()
        super.onViewCreated(view, savedInstanceState)

        args.let {
            val album = it.album
            this.currentAlbum = album
            binding.addTrackSubTittle.text =
                "${getString(R.string.text_add_new_track)} ${album.name}"

            binding.buttonSaveTrack.setOnClickListener {
                addTrackToAlbum(album)
            }
        }
    }

    private fun addTrackToAlbum(album: Album) {
        val newTrack = Track(
            name = binding.trackNameField.text.toString(),
            duration = binding.trackDurationField.text.toString()
        )
        trackNewViewModel.postTrackToAlbum(album, newTrack)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

