package com.example.vinilosmovilapp.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosmovilapp.R
import com.example.vinilosmovilapp.databinding.CollectorItemBinding
import com.example.vinilosmovilapp.models.Collector
import com.example.vinilosmovilapp.ui.AlbumFragmentDirections

class CollectorsAdapter : RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>() {

    // Allows to obtain references of the visual components (views) of each element of the list
    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }
    }

    var collectors: List<Collector> = emptyList()
        set(value) {
            field = value
            // Notify any registered observers that the data set has changed.
            notifyDataSetChanged()
        }

    private lateinit var mContext: Context

    // Create new views (invoked by the layout manager)
    // Inflates the layout (xml file) that represents our elements, and returns an instance of the ViewHolder class that we defined before
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false
        )
        mContext = parent.context
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }

        holder.viewDataBinding.root.setOnClickListener {
            val action =
                AlbumFragmentDirections.actionAlbumFragmentToAlbumDetailFragment(collectors[position].collectorId)
            // Navigate using that action
            Log.d("indice", collectors[position].collectorId.toString())
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }
}