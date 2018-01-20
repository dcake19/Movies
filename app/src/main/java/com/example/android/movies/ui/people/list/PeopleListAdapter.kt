package com.example.android.movies.ui.people.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.loadImage
import com.example.android.movies.ui.people.detailed.PeopleDetailedActivity
import kotlinx.android.synthetic.main.people_list_item.view.*

class PeopleListAdapter(val presenter: PeopleListContract.Presenter, var size:Int = 0)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun display(size:Int){
        this.size = size
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return PeopleViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as PeopleViewHolder
        holder.setData(presenter.getPosterPath(position))
    }

    inner class PeopleViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent?.getContext())
                    .inflate(R.layout.people_list_item,parent,false)){

        fun setData(posterPath:String) = with(itemView){
            image_poster.loadImage(context.getString(R.string.image_start_url),posterPath)

            layout_people_item.setOnClickListener {
                context.startActivity(PeopleDetailedActivity.getIntent(context,
                        presenter.getPersonId(adapterPosition),
                        presenter.getPersonName(adapterPosition)))
            }
        }
    }

}