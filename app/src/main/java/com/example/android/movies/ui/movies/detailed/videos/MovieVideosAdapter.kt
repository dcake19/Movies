package com.example.android.movies.ui.movies.detailed.videos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.movies.R
import kotlinx.android.synthetic.main.movie_details_video_item.view.*


class MovieVideosAdapter(val presenter: MovieVideosContract.Presenter)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var size = 0

    fun display(size:Int){
        this.size = size
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as VideosViewHolder
        holder.setInfo(presenter.getVideoTitle(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
       return VideosViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return size
    }

    inner class VideosViewHolder(parent: ViewGroup?) :RecyclerView.ViewHolder(LayoutInflater.from(
            parent?.getContext()).inflate(R.layout.movie_details_video_item,parent,false)){

        fun setInfo(title:String)= with(itemView){
            text_title_video.text = title
            text_title_video.setOnClickListener {
                presenter.launchVideo(adapterPosition)
            }
        }

    }

}