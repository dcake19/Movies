package com.example.android.movies

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.graphics.Palette
import android.text.TextUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.android.movies.util.ColorUtil
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.movies_list_item.view.*

fun ImageView.loadImage(startUrl:String, imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(startUrl + imageUrl).into(this)
    }
}

fun ImageView.loadImageAndSetBackgroundColor(startUrl:String, imageUrl: String,layout:RelativeLayout) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        val callback = object : Callback{
            override fun onSuccess() {
                Palette.from((this@loadImageAndSetBackgroundColor.drawable as BitmapDrawable).bitmap).generate {
                    palette -> layout.setBackgroundColor(ColorUtil.getDarkColor(palette))
                }
            }
            override fun onError() {
            }
        }

        Picasso.with(context).load(startUrl + imageUrl).into(this,callback)
    }
}