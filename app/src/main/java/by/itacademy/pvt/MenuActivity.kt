package by.itacademy.pvt

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.dz0)
            .setOnClickListener {
            }
    }
}