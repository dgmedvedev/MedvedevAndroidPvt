package by.itacademy.pvt.dz3

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R
import com.bumptech.glide.Glide

class Dz3Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)
        Glide.with(this)
            .load("https://images.wallpaperscraft.ru/image/krasnaya_panda_derevo_listya_lezhit_29285_1920x1080.jpg")
            .circleCrop()
            .into(findViewById(R.id.avatar))
    }
}