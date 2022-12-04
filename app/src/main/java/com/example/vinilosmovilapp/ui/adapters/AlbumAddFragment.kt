package com.example.vinilosmovilapp.ui.adapters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.vinilosmovilapp.viewmodels.AlbumAddViewModel
import com.example.vinilosmovilapp.viewmodels.AlbumViewModel
import com.example.vinilosmovilapp.databinding.AlbumAddFragmentBinding
import com.example.vinilosmovilapp.models.Album

class AlbumAddFragment : Fragment() {

    private var _binding: AlbumAddFragmentBinding? = null
    private val binding get() = _binding!!
    private fun getPattern() = """\d{4}\-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])"""
    private val genres = listOf("Classical", "Salsa", "Rock", "Folk")
    private val recordLabels = listOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Record")

    private lateinit var albumAddViewModel: AlbumAddViewModel
    private lateinit var albumViewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumAddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        albumAddViewModel = ViewModelProvider(this, AlbumAddViewModel.Factory(activity.application))
            .get(AlbumAddViewModel::class.java)
        albumViewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application))
            .get(AlbumViewModel::class.java)
        albumAddViewModel.eventNetworkError.observe(viewLifecycleOwner, {
                isNetworkError -> if (isNetworkError) onNetworkError()
        })
        albumAddViewModel.eventNetworkSuccess.observe(viewLifecycleOwner, {
                isSuccess -> if (isSuccess) onSubmitSuccess()
        })
    }

    private fun onNetworkError() {
        if(!albumAddViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            albumAddViewModel.onNetworkErrorShown()
        }
    }

    private fun onSubmitSuccess() {
        if(!albumAddViewModel.isSuccessShown.value!!) {
            Toast.makeText(activity, "Album Saved", Toast.LENGTH_LONG).show()
            albumAddViewModel.onSuccessShown()
            binding.txtAddAlbumName.text?.clear()
            binding.txtAddAlbumCover.text?.clear()
            binding.txtAddAlbumCalendar.text?.clear()
            binding.txtAddAlbumDescription.text?.clear()
            binding.txtAddAlbumGender.text?.clear()
            binding.txtAddAlbumRecordLabel.text?.clear()
            binding.txtAddAlbumName.requestFocus()
//            goToAlbums()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddAlbum.setOnClickListener {
            addAlbum()
        }
    }

    private fun addAlbum() {
        val newAlbum = Album(
            albumId = 0,
            name = binding.txtAddAlbumName.text.toString(),
            cover = binding.txtAddAlbumCover.text.toString(),
            releaseDate = binding.txtAddAlbumCalendar.text.toString(),
            description = binding.txtAddAlbumDescription.text.toString(),
            genre = binding.txtAddAlbumGender.text.toString(),
            recordLabel = binding.txtAddAlbumRecordLabel.toString(),
        )
        if (newAlbum.name.isEmpty() || newAlbum.cover?.isEmpty()!! || newAlbum.description?.isEmpty()!!){
            Toast.makeText(activity, "The data should not be empty", Toast.LENGTH_LONG).show()
            return
        }

        if (!newAlbum.releaseDate!!.matches(getPattern().toRegex())){
            Toast.makeText(activity, "The release date not apply the format yyyy-mm-dd", Toast.LENGTH_LONG).show()
            return
        }

        if (newAlbum.genre !in genres){
            Toast.makeText(activity, "The genre is not support", Toast.LENGTH_LONG).show()
            return
        }

        if (newAlbum.recordLabel !in recordLabels){
            Toast.makeText(activity, "The recordLabels is not support", Toast.LENGTH_LONG).show()
            return
        }
        albumAddViewModel.postAlbum(newAlbum)
    }

/*    private fun goToAlbums() {
        albumViewModel.refreshDataFromNetwork()
        findNavController()
            .navigate(AlbumAddFragmentDirections.actionCreateAlbumToAlbums())
    }*/
}