package by.itacademy.pvt.dz5.part2

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.R
import android.graphics.drawable.AnimationDrawable

class Dz5Activity2 : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_dz5)

        val mImageViewEmptying = findViewById<ImageView>(R.id.imageview_animation_list_emptying)
        (mImageViewEmptying.background as AnimationDrawable).start()

        val mImageViewEmptying1 = findViewById<ImageView>(R.id.imageview_animation_list_filling)
        (mImageViewEmptying1.background as AnimationDrawable).start()

        val mImageViewSelector = findViewById<ImageView>(R.id.imageview_animated_selector)
        mImageViewSelector.setOnClickListener { mImageViewSelector.isActivated = !mImageViewSelector.isActivated }
    }
}