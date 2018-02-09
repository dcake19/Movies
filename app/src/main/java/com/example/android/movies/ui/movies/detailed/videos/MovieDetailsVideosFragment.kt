package com.example.android.movies.ui.movies.detailed.videos

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.movie_details_videos_fragment.*
import kotlinx.android.synthetic.main.people_list_fragment.*
import javax.inject.Inject

class MovieDetailsVideosFragment : Fragment(),MovieVideosContract.View {

    @Inject lateinit var presenter: MovieVideosContract.Presenter
    lateinit var adapter: MovieVideosAdapter

    override fun onAttach(context: Context?) {
        AndroidInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = true

        presenter.addView(this)
        adapter = MovieVideosAdapter(presenter)

        return inflater!!.inflate(R.layout.movie_details_videos_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadVideoLinks(arguments.getInt(MovieDetailsActivity.MOVIE_ID))
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView(){
        recycler_videos.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }
        if(recycler_videos.adapter == null)
            recycler_videos.adapter = adapter

    }

    override fun display(size: Int) {
        adapter.display(size)
    }

}