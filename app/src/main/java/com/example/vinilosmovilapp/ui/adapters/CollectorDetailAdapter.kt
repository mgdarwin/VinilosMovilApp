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
import com.example.vinilosmovilapp.databinding.CollectorDetailItemBinding
import com.example.vinilosmovilapp.models.Collector

class CollectorDetailAdapter : RecyclerView.Adapter<CollectorDetailAdapter.CollectorDetailViewHolder>(){

    var collectors :List<Collector> = emptyList()
        set(value) {
            field = value
            notifyItemRangeChanged(0, field.size)
        }

    private lateinit var mContext: Context

    class CollectorDetailViewHolder(val viewDataBinding: CollectorDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_detail_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorDetailViewHolder {
        val withDataBinding: CollectorDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorDetailViewHolder.LAYOUT,
            parent,
            false)
        mContext = parent.context
        return CollectorDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }

        val collector: Collector = collectors.get(position)
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }

//        with(holder) {
//            Glide.with(mContext)
//                .load(collector.cover)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .centerCrop()
//                .into(viewDataBinding.collectorDetailCover)
//        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }
}