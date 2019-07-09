package by.itacademy.pvt.dz2

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadCirclePicture(activity: Activity, url: String, imageView: ImageView) {
    Glide.with(activity)
        .load(url)
        .circleCrop()
        .into(imageView)
}

fun loadCirclePicture(activity: Activity, imageId: Int?, imageView: ImageView) {
    Glide.with(activity)
        .load(imageId)
        .circleCrop()
        .into(imageView)
}