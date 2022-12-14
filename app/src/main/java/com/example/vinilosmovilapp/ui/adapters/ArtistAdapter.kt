package com.example.vinilosmovilapp.ui.adapters

import android.content.Context
import android.util.Log
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
import com.example.vinilosmovilapp.databinding.ArtistListItemBinding
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.ui.ArtistListFragmentDirections


class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>(){

    // Allows to obtain references of the visual components (views) of each element of the list
    class ArtistViewHolder(val viewDataBinding: ArtistListItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_list_item
        }
    }

    var artists :List<Artist> = emptyList()
        set(value) {
            field = value
            // Notify any registered observers that the data set has changed.
            notifyDataSetChanged()
        }

    private lateinit var mContext: Context

    // Create new views (invoked by the layout manager)
    // Inflates the layout (xml file) that represents our elements, and returns an instance of the ViewHolder class that we defined before
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        mContext = parent.context
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }

        holder.viewDataBinding.root.setOnClickListener {
            val action = ArtistListFragmentDirections.actionArtistFragmentToArtistDetailFragment(artists[position].artistId)
            // Navigate using that action
            Log.d("indice", artists[position].artistId.toString())
            holder.viewDataBinding.root.findNavController().navigate(action)
        }

        val artist: Artist = artists.get(position)
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }

        with(holder) {
            val radius = 40; // corner radius, higher value = more rounded
            val margin = 10; // crop margin, set to 0 for corners with no crop
            Glide.with(mContext)
                .load(artist.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .transform(RoundedCorners(radius))
                .into(viewDataBinding.artistImg)
        }
    }
    override fun getItemCount(): Int {
        return artists.size
    }
}
