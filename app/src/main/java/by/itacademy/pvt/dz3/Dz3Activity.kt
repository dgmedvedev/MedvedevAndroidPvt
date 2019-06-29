package by.itacademy.pvt.dz3

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dz3.*

class Dz3Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)

        Picasso.get()
            .load("https://images.wallpaperscraft.ru/image/krasnaya_panda_derevo_listya_lezhit_29285_1920x1080.jpg")
            .transform(Border(50, 1))
            .resize(100, 100)
            .centerCrop().into(avatar)
    }
}