package com.example.vinilosmovilapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.vinilosmovilapp.R
import com.example.vinilosmovilapp.databinding.AlbumItemBinding
import com.example.vinilosmovilapp.models.Album

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    private lateinit var mContext: Context

    // Allows to obtain references of the visual components (views) of each element of the list
    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            // Notify any registered observers that the data set has changed.
            notifyDataSetChanged()
        }

    // Create new views (invoked by the layout manager)
    // Inflates the layout (xml file) that represents our elements, and returns an instance of the ViewHolder class that we defined before
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        mContext = parent.context
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }

        /*
        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(albums[position].albumId)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }

         */
        val album: Album = albums.get(position)
        holder.viewDataBinding.also {
            it.album = albums[position]
        }

        with(holder) {
            val radius = 40; // corner radius, higher value = more rounded
            val margin = 10; // crop margin, set to 0 for corners with no crop
            Glide.with(mContext)
                .load(album.cover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .transform(RoundedCorners(radius))
                .into(viewDataBinding.albumImg)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}