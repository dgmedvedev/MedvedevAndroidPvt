package by.itacademy.pvt.dz5.part2

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.R
import android.graphics.drawable.AnimationDrawable

class Dz5Activity2 : Activity() {
    private lateinit var imageViewFilling: ImageView
    private lateinit var imageViewEmptying: ImageView
    private lateinit var imageViewSelector: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_dz5)

        imageViewFilling = findViewById(R.id.imageview_animation_list_filling)
        imageViewEmptying = findViewById(R.id.imageview_animation_list_emptying)
        imageViewSelector = findViewById(R.id.imageview_animated_selector)
    }

    override fun onResume() {
        super.onResume()

        (imageViewFilling.background as AnimationDrawable).start()

        (imageViewEmptying.background as AnimationDrawable).start()

        imageViewSelector.setOnClickListener {
            imageViewSelector.isActivated = !imageViewSelector.isActivated
        }
    }

    override fun onPause() {
        super.onPause()

        (imageViewFilling.background as AnimationDrawable).stop()

        (imageViewEmptying.background as AnimationDrawable).stop()
    }
}