package by.itacademy.pvt.dz11

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.itacademy.pvt.R
import by.itacademy.pvt.dz11.mvp.Dz11MvpActivity
import by.itacademy.pvt.dz11.mvvm.Dz11MvvmActivity

class Dz11MenuActivity : Activity() {
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
        val intent = Intent(this, Dz11MvpActivity::class.java)
        startActivity(intent)
    }
    private fun startPart2() {
        val intent = Intent(this, Dz11MvvmActivity::class.java)
        startActivity(intent)
    }
}