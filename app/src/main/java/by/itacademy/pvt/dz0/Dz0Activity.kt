package by.itacademy.pvt.dz0

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import by.itacademy.pvt.R

class Dz0Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz0)

        findViewById<Button>(R.id.buttonDz0)
            .setOnClickListener {
                startDz0()
            }
        findViewById<TextView>(R.id.textViewOneDz0)
            .setOnClickListener {
                startDz0()
            }
        findViewById<TextView>(R.id.textViewTwoDz0)
            .setOnClickListener {
                startDz0()
            }
    }

    private fun startDz0() {
        val textViewOne: TextView = findViewById(R.id.textViewOneDz0)
        val textViewTwo: TextView = findViewById(R.id.textViewTwoDz0)

        val tempText = textViewOne.text
        textViewOne.setText(textViewTwo.getText())
        textViewTwo.setText(tempText)

        val tempColor = textViewOne.background
        textViewOne.background = textViewTwo.background
        textViewTwo.background = tempColor
    }
}