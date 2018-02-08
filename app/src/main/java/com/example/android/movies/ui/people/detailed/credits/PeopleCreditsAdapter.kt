package com.example.android.movies.ui.people.detailed.credits

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.loadImage
import com.example.android.movies.loadImageAndSetBackgroundColor
import com.example.android.movies.ui.movies.CreditsType
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import com.example.android.movies.util.ColorUtil
import kotlinx.android.synthetic.main.people_detailed_cast_item.view.*


class PeopleCreditsAdapter(val presenter: PeopleCreditsContract.Presenter, val creditsType:Int)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var sizeCast:Int = 0
    var sizeCrew:Int = 0

    fun display(sizeCast:Int,sizeCrew:Int){
        this.sizeCast = sizeCast
        this.sizeCrew = sizeCrew
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as CreditsViewHolder
        if (creditsType==CreditsType.CAST){
            holder.setInfo(presenter.getCastMovieId(position),
                    presenter.getCastMovieName(position),
                    presenter.getCharacter(position),
                    presenter.getCastMoviePosterPath(position))
        }else{
            holder.setInfo(presenter.getCrewMovieId(position),
                    presenter.getCrewMovieName(position),
                    presenter.getJob(position),
                    presenter.getCrewMoviePosterPath(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return CreditsViewHolder(parent)
    }

    override fun getItemCount(): Int {
        if (creditsType==CreditsType.CAST) return sizeCast
        else return sizeCrew
    }

    inner class CreditsViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(LayoutInflater.from(
            parent?.getContext()).inflate(R.layout.people_detailed_cast_item,parent,false)) {

        fun setInfo(id: Int, title: String, characterOrJob: String, posterPath: String) = with(itemView) {
            text_title.text = title
            text_character_or_job.text = characterOrJob
            image_poster.loadImageAndSetBackgroundColor(context.getString(R.string.image_start_url),
                    posterPath, layout_cast_item)
            layout_cast_item.setOnClickListener {
                val backdropPath: String
                if (creditsType == CreditsType.CAST)
                    backdropPath = presenter.getCastMovieBackdropPath(adapterPosition)
                else
                    backdropPath = presenter.getCrewMovieBackdropPath(adapterPosition)

                if (image_poster.drawable == null) {
                    context.startActivity(MovieDetailsActivity.getIntent(context, id, title,
                            Color.BLACK, Color.YELLOW,
                            backdropPath))
                } else {
                    Palette.from((image_poster.drawable as BitmapDrawable).bitmap).generate { palette ->
                        context.startActivity(
                                MovieDetailsActivity.getIntent(context,
                                        id,
                                        title,
                                        ColorUtil.getDarkColor(palette),
                                        ColorUtil.getLightColor(palette),
                                        backdropPath))
                    }
                }
            }

        }
    }
}