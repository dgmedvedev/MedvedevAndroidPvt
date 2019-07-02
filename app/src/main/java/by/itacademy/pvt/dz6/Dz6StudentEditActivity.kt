package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import by.itacademy.pvt.R

class Dz6StudentEditActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student_dz6)

        var name = findViewById<EditText>(R.id.nameEditText).text.toString()
        if (name.equals("")) {
            name = resources.getString(R.string.anonymous)
        }

        findViewById<Button>(R.id.save)
            .setOnClickListener {
                try {
                    Singleton.getListStudent().add(
                        Student(
                            System.currentTimeMillis(),
                            "https://clck.ru/GskPq",
                            name,
                            findViewById<EditText>(R.id.ageEditText).text.toString().toInt()
                        )
                    )
                //    val imageView = findViewById<ImageView>(R.id.photo_student)
                //    loadCirclePicture(this,"https://clck.ru/GskPq",imageView)

                    startDz6StudentListActivity()
                } catch (nfe: NumberFormatException) {
                    Toast.makeText(this, "Введите возраст", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun startDz6StudentListActivity() {
        val intent = Intent(this, Dz6StudentListActivity::class.java)
        startActivity(intent)
    }
}