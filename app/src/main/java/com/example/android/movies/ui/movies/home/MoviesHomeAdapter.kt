package com.example.android.movies.ui.movies.home

import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.loadImage
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import kotlinx.android.synthetic.main.movies_item_small.view.*
import android.support.v7.graphics.Palette

import com.example.android.movies.util.ColorUtil


class MoviesHomeAdapter(val presenter: MoviesContract.Presenter, var size:Int = 0) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun update(size: Int) {
        this.size = size
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as MoviesViewHolder
        holder.setImage(presenter.getTitle(position),presenter.getPosterPath(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return MoviesViewHolder(parent)
    }

    override fun getItemCount(): Int {
       return size
    }

    inner class MoviesViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent?.getContext()).inflate(R.layout.movies_item_small,parent,false)) {

        fun setImage(title:String,posterPath:String) = with(itemView){
            image_poster.loadImage(context.getString(R.string.image_start_url),posterPath)
            text_title.text = title
            layout_movies_item.setOnClickListener {
                val bitmap = (image_poster.drawable as BitmapDrawable).bitmap

                Palette.from(bitmap).generate {
                    palette ->
                    context.startActivity(
                            MovieDetailsActivity.getIntent(context,
                                    presenter.getMovieId(adapterPosition),
                                    presenter.getTitle(adapterPosition),
                                    ColorUtil.getDarkColor(palette),
                                    ColorUtil.getLightColor(palette)))
                }


            }
        }

    }

}