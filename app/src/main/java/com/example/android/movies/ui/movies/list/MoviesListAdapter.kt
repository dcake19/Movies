package com.example.android.movies.ui.movies.list

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.loadImage
import com.example.android.movies.loadImageAndSetBackgroundColor
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import com.example.android.movies.util.ColorUtil
import kotlinx.android.synthetic.main.movies_list_item.view.*

class MoviesListAdapter(val presenter: MoviesContract.Presenter, var size:Int = 0)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun update(size: Int) {
        this.size = size
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as MoviesViewHolder
        holder.setData(presenter.getTitle(position),
                presenter.getPosterPath(position),
                presenter.getYear(position),
                presenter.getVoteAverage(position),
                presenter.getVoteCount(position),
                presenter.getRatingBackgroundColor(position),
                presenter.getRatingTextColor(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return MoviesViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return size
    }

    inner class MoviesViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent?.getContext()).inflate(R.layout.movies_list_item,parent,false)){

        fun setData(title:String,posterPath:String,year:String,
                    userScore: String, voteCount: String,
                    ratingBackgroundColor:Int,ratingTextColor:Int) = with(itemView){

            text_movie_title.text = title
            image_poster.loadImageAndSetBackgroundColor(context.getString(R.string.image_start_url),posterPath,layout_movies_item)

//            Palette.from((image_poster.drawable as BitmapDrawable).bitmap).generate {
//                palette -> layout_movies_item.setBackgroundColor(ColorUtil.getDarkColor(palette))
//            }

            text_year.text = year
            text_user_score.text = userScore
            text_vote_count.text = voteCount
            val voteAverageCircle = text_user_score.background as GradientDrawable
            voteAverageCircle.setColor(ratingBackgroundColor)
            text_user_score.setTextColor(ratingTextColor)

            layout_movies_item.setOnClickListener {
                if (image_poster.drawable==null){
                    context.startActivity(
                            MovieDetailsActivity.getIntent(context,
                                    presenter.getMovieId(adapterPosition),
                                    presenter.getTitle(adapterPosition),
                                    Color.BLACK,
                                    Color.YELLOW))
                }else {
                    Palette.from((image_poster.drawable as BitmapDrawable).bitmap).generate { palette ->
                        context.startActivity(
                                MovieDetailsActivity.getIntent(context,
                                        presenter.getMovieId(adapterPosition),
                                        presenter.getTitle(adapterPosition),
                                        ColorUtil.getDarkColor(palette),
                                        ColorUtil.getLightColor(palette)))
                    }
                }
//
            }
        }


    }

}