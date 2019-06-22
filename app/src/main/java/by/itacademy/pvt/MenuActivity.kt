package by.itacademy.pvt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.itacademy.pvt.dz4.Dz4Activity

class MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.dz4)
            .setOnClickListener {
                startDz4()
            }
    }

    private fun startDz4() {
        val intent = Intent(this, Dz4Activity::class.java)
        startActivity(intent)
    }
}