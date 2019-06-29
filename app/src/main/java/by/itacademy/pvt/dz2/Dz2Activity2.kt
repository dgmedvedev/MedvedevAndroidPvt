package by.itacademy.pvt.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import by.itacademy.pvt.R
import com.bumptech.glide.Glide

class Dz2Activity2 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_dz2)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility = INVISIBLE

        findViewById<Button>(R.id.picture_download)
            .setOnClickListener {
                progressBar.visibility = VISIBLE

                Glide.with(this)
                    .load("https://clck.ru/GgBAr")
                    .circleCrop()
                    .into(findViewById(R.id.picture_view))
            }
    }
}