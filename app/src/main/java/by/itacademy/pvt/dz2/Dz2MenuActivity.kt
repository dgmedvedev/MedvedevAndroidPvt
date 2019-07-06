package by.itacademy.pvt.dz2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.itacademy.pvt.R

class Dz2MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_dz2)

        findViewById<Button>(R.id.part1)
            .setOnClickListener {
                startPart1()
            }
        findViewById<Button>(R.id.part2)
            .setOnClickListener {
                startPart2()
            }
    }

    private fun startPart1() {
        val intent = Intent(this, Dz2Activity1::class.java)
        startActivity(intent)
    }
    private fun startPart2() {
        val intent = Intent(this, Dz2Activity2::class.java)
        startActivity(intent)
    }
}