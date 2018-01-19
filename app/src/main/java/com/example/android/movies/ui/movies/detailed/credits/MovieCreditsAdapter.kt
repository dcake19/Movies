package com.example.android.movies.ui.movies.detailed.credits

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.loadImage
import com.example.android.movies.ui.movies.CreditsType
import com.example.android.movies.ui.people.detailed.PeopleDetailedActivity
import kotlinx.android.synthetic.main.movie_details_cast_item.view.*
import kotlinx.android.synthetic.main.movie_details_cast_title.view.*


class MovieCreditsAdapter(val presenter: MovieCreditsContract.Presenter,val creditsType:Int)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var sizeCast:Int = 0
    var sizeCrew:Int = 0

    fun display(sizeCast:Int,sizeCrew:Int){
        this.sizeCast = sizeCast
        this.sizeCrew = sizeCrew
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return CreditsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as CreditsViewHolder
        if(creditsType== CreditsType.CAST){
            holder.setInfo(presenter.getCastName(position),
                    presenter.getCharacter(position),
                    presenter.getCastPosterPath(position ))
        }else{
            holder.setInfo(presenter.getCrewName(position ),
                    presenter.getJob(position ),
                    presenter.getCrewPosterPath(position ))
        }
    }

    override fun getItemCount(): Int {
        if(creditsType== CreditsType.CAST && sizeCast >0) return sizeCast+1
        else if(sizeCrew>0) return sizeCrew+1
        else return 0
    }

    inner class CreditsViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(LayoutInflater.from(
            parent?.getContext()).inflate(R.layout.movie_details_cast_item,parent,false)){

        fun setInfo(name:String,characterOrJob:String,posterPath:String) = with(itemView){
            image_poster.loadImage(context.getString(R.string.image_start_url),posterPath)
            text_name.text = name
            text_character_or_job.text = characterOrJob

            layout_movies_item.setOnClickListener {
                val id: Int
                val name: String
                if(creditsType==CreditsType.CAST){
                    id = presenter.getCastId(adapterPosition)
                    name = presenter.getCastName(adapterPosition)
                }
                else{
                    id = presenter.getCrewId(adapterPosition)
                    name = presenter.getCrewName(adapterPosition)
                }
                context.startActivity(PeopleDetailedActivity.getIntent(context,id,name))
            }
        }
    }
}