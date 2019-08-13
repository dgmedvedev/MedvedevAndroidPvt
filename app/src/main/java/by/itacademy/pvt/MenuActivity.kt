package by.itacademy.pvt

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import by.itacademy.pvt.dz0.Dz0Activity
import by.itacademy.pvt.dz1.Dz1Activity
import by.itacademy.pvt.dz11.Dz11MenuActivity
import by.itacademy.pvt.dz12.Dz12MvpActivity
import by.itacademy.pvt.dz2.Dz2MenuActivity
import by.itacademy.pvt.dz3.Dz3Activity
import by.itacademy.pvt.dz4.Dz4Activity
import by.itacademy.pvt.dz5.Dz5MenuActivity
import by.itacademy.pvt.dz6.Dz6StudentListActivity
import by.itacademy.pvt.dz8.Dz8FragmentActivity
import by.itacademy.pvt.dz9.Dz9Activity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        dz0.setOnClickListener {
            start(Dz0Activity())
        }
        dz1.setOnClickListener {
            start(Dz1Activity())
        }
        dz2.setOnClickListener {
            start(Dz2MenuActivity())
        }
        dz3.setOnClickListener {
            start(Dz3Activity())
        }
        dz4.setOnClickListener {
            start(Dz4Activity())
        }
        dz5.setOnClickListener {
            start(Dz5MenuActivity())
        }
        dz6.setOnClickListener {
            start(Dz6StudentListActivity())
        }
        dz8.setOnClickListener {
            start(Dz8FragmentActivity())
        }
        dz9.setOnClickListener {
            start(Dz9Activity())
        }
        dz11.setOnClickListener {
            start(Dz11MenuActivity())
        }
        dz12.setOnClickListener {
            start(Dz12MvpActivity())
        }
    }

    private fun start(activity: Activity) {
        startActivity(Intent(this, activity::class.java))
    }
}