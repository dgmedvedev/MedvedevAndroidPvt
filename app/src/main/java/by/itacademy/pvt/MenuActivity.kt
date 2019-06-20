package by.itacademy.pvt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.itacademy.pvt.dz2.Dz2MenuActivity

class MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.dz0)
            .setOnClickListener {
            }

        findViewById<Button>(R.id.dz2)
            .setOnClickListener {
                startDz2()
            }
    }

    private fun startDz2() {
        val intent = Intent(this, Dz2MenuActivity::class.java)
        startActivity(intent)
    }
}