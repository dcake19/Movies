package com.example.android.movies.ui.movies.list.discover

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.example.android.movies.R
import kotlinx.android.synthetic.main.movies_discover_genres_dialog.*


class GenresDialog: DialogFragment() {

    companion object {
        const val INCLUDE = "include"
        const val INITIAL = "initial"
    }

    private var include = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        include = arguments.getBoolean(INCLUDE)

        return inflater!!.inflate(R.layout.movies_discover_genres_dialog, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        setCheckBoxes(arguments.getBooleanArray(INITIAL))

        if (include) btn_select_genres.text = getString(R.string.include_genres)
        else btn_select_genres.text = getString(R.string.exclude_genres)

        btn_select_genres.setOnClickListener {
            val discoverGenres = activity as DiscoverGenres
            discoverGenres.setGenres(getCheckedGenres(),include)
            dismiss()
        }
        btn_cancel.setOnClickListener { dismiss() }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getCheckedGenres():BooleanArray{
        val checkedGenres = BooleanArray(19)

        for (i in 0..18)
            checkedGenres[i] = getCheckBox(i).isChecked

        return checkedGenres
    }

    private fun setCheckBoxes(genres:BooleanArray){
        for (i in 0..18)
            getCheckBox(i).isChecked = genres[i]
    }

    private fun getCheckBox(index:Int):CheckBox{
        when(index){
            0 -> return check_box_action
            1 -> return check_box_adventure
            2 -> return check_box_animation
            3 -> return check_box_comedy
            4 -> return check_box_crime
            5 -> return check_box_documentary
            6 -> return check_box_drama
            7 -> return check_box_family
            8 -> return check_box_fantasy
            9 -> return check_box_history
            10 -> return check_box_horror
            11 -> return check_box_music
            12 -> return check_box_mystery
            13 -> return check_box_romance
            14 -> return check_box_sci_fi
            15 -> return check_box_tv_movie
            16 -> return check_box_thriller
            17 -> return check_box_war
            else -> return check_box_western
        }
    }


}