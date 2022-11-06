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
import com.example.vinilosmovilapp.databinding.AlbumDetailItemBinding
import com.example.vinilosmovilapp.models.Album

class AlbumDetailAdapter : RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>(){

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyItemRangeChanged(0, field.size)
        }

    private lateinit var mContext: Context

    class AlbumDetailViewHolder(val viewDataBinding: AlbumDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_detail_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val withDataBinding: AlbumDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumDetailViewHolder.LAYOUT,
            parent,
            false)
        mContext = parent.context
        return AlbumDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }

        val album: Album = albums.get(position)
        holder.viewDataBinding.also {
            it.album = albums[position]
        }

        with(holder) {
            Glide.with(mContext)
                .load(album.cover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(viewDataBinding.albumDetailCover)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}