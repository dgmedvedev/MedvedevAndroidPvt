package by.itacademy.pvt.dz2

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import by.itacademy.pvt.R
import com.squareup.picasso.Picasso

class Dz2Activity2 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_dz2)

        findViewById<Button>(R.id.picture_download)
            .setOnClickListener {
                Picasso.get()
                    .load("https://mobimg.b-cdn.net/pic/v2/gallery/preview/tigry-zhivotnye-24572.jpg")
                    .into(findViewById<ImageView>(R.id.picture_view))
            }
    }
}