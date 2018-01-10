package com.example.android.movies

import android.support.v4.app.Fragment
import android.text.TextUtils
import android.widget.ImageView
import com.squareup.picasso.Picasso

public fun ImageView.loadImage(startUrl:String, imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(startUrl+ imageUrl).into(this)
    }
}

//val Fragment.app: App
//    get() = activity.application as App