package com.example.android.movies.ui.movies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.android.movies.R
import com.example.android.movies.loadImage
import kotlinx.android.synthetic.main.movies_item_small.view.*
import com.squareup.picasso.Picasso

class MoviesAdapter(val presenter: MoviesContract.Presenter,var size:Int = 0) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun update(size: Int) {
        this.size = size
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as MoviesViewHolder
        holder.setImage(presenter.getPosterPath(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return MoviesViewHolder(parent)
    }

    override fun getItemCount(): Int {
       return size
    }

    inner class MoviesViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent?.getContext()).inflate(R.layout.movies_item_small,parent,false)),
            View.OnClickListener{

        fun setImage(posterPath:String) = with(itemView){
            image_poster.loadImage(context.getString(R.string.image_start_url),posterPath)
        }

        override fun onClick(p0: View?) {
        }
    }

}