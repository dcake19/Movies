package com.example.android.movies.ui.movies.detailed.credits

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.loadImage
import kotlinx.android.synthetic.main.movie_details_cast_item.view.*
import kotlinx.android.synthetic.main.movie_details_cast_title.view.*


class MovieCreditsAdapter(val presenter: MovieCreditsContract.Presenter)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var sizeCast:Int = 0
    var sizeCrew:Int = 0

    fun display(sizeCast:Int,sizeCrew:Int){
        this.sizeCast = sizeCast
        this.sizeCrew = sizeCrew
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1) return TitleViewHolder(parent)
        else return CreditsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
//        if (position==0){
//            holder as TitleViewHolder
//            if (sizeCast>0)
//                holder.setTitle(0)
//            else if(sizeCrew>0)
//                holder.setTitle(1)
//        }else if (position==sizeCast+1){
//            holder as TitleViewHolder
//            if(sizeCrew>0)
//                holder.setTitle(1)
//        }else if(sizeCast>0 && position<=sizeCast) {
//            holder as CreditsViewHolder
//            holder.setInfo(presenter.getCastName(position-1),
//                    presenter.getCharacter(position-1),
//                    presenter.getCastPosterPath(position-1))
//        }else{
//            holder as CreditsViewHolder
//            var crewPosition = position - 1 - sizeCast
//            if(sizeCast>0)crewPosition--
//            holder.setInfo(presenter.getCrewtName(crewPosition),
//                    presenter.getJob(crewPosition),
//                    presenter.getCrewPosterPath(crewPosition))
//        }

        val type = getItemViewType(position)
        if (type==1){
            holder as TitleViewHolder
            if(position==0 && sizeCast>0) holder.setTitle(0)
            else holder.setTitle(1)
        }else{
            holder as CreditsViewHolder
            if (position<=sizeCast) {
                holder.setInfo(presenter.getCastName(position - 1),
                        presenter.getCharacter(position - 1),
                        presenter.getCastPosterPath(position - 1))
            }
            else {
                var crewPosition = position - 1 - sizeCast
                if(sizeCast>0)crewPosition--

                holder.setInfo(presenter.getCrewtName(crewPosition),
                        presenter.getJob(crewPosition),
                        presenter.getCrewPosterPath(crewPosition))
            }
        }
    }

    override fun getItemCount(): Int {
        var size = sizeCast + sizeCrew
        if (sizeCast>0) size++
        if (sizeCrew>0) size++
        return size
    }

    override fun getItemViewType(position: Int): Int {
        val type:Int
        if(position==0 || (position==sizeCast+1 && sizeCast>0) ) type = 1
        else type = 2

        return type
    }

    inner class TitleViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(LayoutInflater.from(
            parent?.getContext()).inflate(R.layout.movie_details_cast_title,parent,false)){

        fun setTitle(i:Int) = with(itemView){
            val title:String
            if(i==0) title = context.getString(R.string.title_cast)
            else title = context.getString(R.string.title_crew)
            text_title.text = title
        }
    }

    inner class CreditsViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(LayoutInflater.from(
            parent?.getContext()).inflate(R.layout.movie_details_cast_item,parent,false)){

        fun setInfo(name:String,characterOrJob:String,posterPath:String) = with(itemView){
            image_poster.loadImage(context.getString(R.string.image_start_url),posterPath)
            text_name.text = name
            text_character_or_job.text = characterOrJob
        }
    }



}