package by.itacademy.pvt.dz5

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R

class Dz5Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5)

        val myViewDz5 = findViewById<MyViewDz5>(R.id.myViewDz5)
        myViewDz5.myArray = intArrayOf(10, 20, 30, 40, 50)
        myViewDz5.sizeArray = myViewDz5.myArray.size
    }
}