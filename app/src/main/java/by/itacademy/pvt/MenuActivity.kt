package by.itacademy.pvt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.itacademy.pvt.dz1.Dz1Activity

class MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.dz0)
            .setOnClickListener {

            }

        findViewById<Button>(R.id.dz1)
            .setOnClickListener {
                startDz1()
            }
    }

    private fun startDz1() {
        val intent = Intent(this, Dz1Activity::class.java)
        startActivity(intent)
    }
}