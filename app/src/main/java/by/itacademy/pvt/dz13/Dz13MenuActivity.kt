package by.itacademy.pvt.dz13

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.itacademy.pvt.R
import by.itacademy.pvt.dz13.part2.Dz13TimerActivity

class Dz13MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_two_button)

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
        val intent = Intent(this, Dz13MvvmActivity::class.java)
        startActivity(intent)
    }
    private fun startPart2() {
        val intent = Intent(this, Dz13TimerActivity::class.java)
        startActivity(intent)
    }
}