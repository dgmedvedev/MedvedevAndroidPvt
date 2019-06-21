package by.itacademy.pvt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.itacademy.pvt.dz3.Dz3Activity

class MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.dz3)
            .setOnClickListener {
                startDz3()
            }
    }

    private fun startDz3() {
        val intent = Intent(this, Dz3Activity::class.java)
        startActivity(intent)
    }
}