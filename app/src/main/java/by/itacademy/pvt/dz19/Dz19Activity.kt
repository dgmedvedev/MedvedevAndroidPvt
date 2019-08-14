package by.itacademy.pvt.dz19

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R
import io.reactivex.subjects.PublishSubject

class Dz19Activity : Activity() {

    private val subject = PublishSubject.create<String>() // subject - это как LiveData, но более продвинутая
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz19)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}