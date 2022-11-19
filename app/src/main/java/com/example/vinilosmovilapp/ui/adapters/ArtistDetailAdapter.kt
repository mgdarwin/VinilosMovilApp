package com.example.vinilosmovilapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.vinilosmovilapp.R
import com.example.vinilosmovilapp.databinding.ArtistDetailItemBinding
import com.example.vinilosmovilapp.models.Artist

class ArtistDetailAdapter : RecyclerView.Adapter<ArtistDetailAdapter.ArtistDetailViewHolder>(){

    var artists :List<Artist> = emptyList()
        set(value) {
            field = value
            notifyItemRangeChanged(0, field.size)
        }

    private lateinit var mContext: Context

    class ArtistDetailViewHolder(val viewDataBinding: ArtistDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_detail_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistDetailViewHolder {
        val withDataBinding: ArtistDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistDetailViewHolder.LAYOUT,
            parent,
            false)
        mContext = parent.context
        return ArtistDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }

        val artist: Artist = artists.get(position)
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }

        with(holder) {
            Glide.with(mContext)
                .load(artist.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(viewDataBinding.artistDetailImage)
        }
    }

    override fun getItemCount(): Int {
        return artists.size
    }
}