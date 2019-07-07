package by.itacademy.pvt.dz5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.itacademy.pvt.R
import by.itacademy.pvt.dz5.part1.Dz5Activity1
import by.itacademy.pvt.dz5.part2.Dz5Activity2

class Dz5MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_dz5)

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
        val intent = Intent(this, Dz5Activity1::class.java)
        startActivity(intent)
    }
    private fun startPart2() {
        val intent = Intent(this, Dz5Activity2::class.java)
        startActivity(intent)
    }
}