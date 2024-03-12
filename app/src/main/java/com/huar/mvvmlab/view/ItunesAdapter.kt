package com.huar.mvvmlab.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.huar.mvvmlab.R
import com.huar.mvvmlab.model.ItunesData

class ItunesAdapter(private var itunes: MutableList<ItunesData>) :
    RecyclerView.Adapter<ItunesAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.music_item, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        vh.bind(itunes[position])
    }

    override fun getItemCount(): Int {
        return itunes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: MutableList<ItunesData>) {
        itunes = data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTrackName: TextView = view.findViewById(R.id.textTrackName)
        private val textArtistName: TextView = view.findViewById(R.id.textArtistName)
        private val textPrice: TextView = view.findViewById(R.id.textPrice)
        private val imageView: ImageView = view.findViewById(R.id.imageView)
        @SuppressLint("SetTextI18n")
        fun bind(itunes: ItunesData) {
            textTrackName.text = itunes.trackName
            textArtistName.text = itunes.artistName
            textPrice.text = "$${itunes.trackPrice}"
            Glide.with(imageView.context).load(itunes.artworkUrl100).into(imageView)
        }
    }
}