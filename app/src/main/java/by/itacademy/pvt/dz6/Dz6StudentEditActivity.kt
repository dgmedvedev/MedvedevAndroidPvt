package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import by.itacademy.pvt.R

class Dz6StudentEditActivity : Activity() {

    private var idStudent = 0L

    private val pattern = Patterns.WEB_URL

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getIntent(context: Context, idStudent: Long = System.currentTimeMillis()): Intent {
            val intent = Intent(context, Dz6StudentEditActivity::class.java)
            intent.putExtra(ID_STUDENT, idStudent)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student_dz6)

        findViewById<Button>(R.id.save)
            .setOnClickListener {
                val name = findViewById<EditText>(R.id.nameEditText).text.toString()
                //        var url = findViewById<EditText>(R.id.urlEditText).text.toString()
                val url = "https://clck.ru/GskPq" // для тестирования приложения,
                // что бы не вводить каждый раз

                try {
                    if (!pattern.matcher(url).matches()) throw HttpFormatException()

                    Singleton.getListStudent().add(
                        Student(
                            System.currentTimeMillis(),
                            url,
                            if (name == "") {
                                resources.getString(R.string.anonymous)
                            } else name,
                            findViewById<EditText>(R.id.ageEditText).text.toString().toInt()
                        )
                    )
                    startDz6StudentListActivity()
                } catch (nfe: NumberFormatException) {
                    Toast.makeText(this, "Введите возраст", Toast.LENGTH_SHORT).show()
                } catch (hfe: HttpFormatException) {
                    Toast.makeText(this, "Неверно введен URL", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun startDz6StudentListActivity() {
        val intent = Intent(this, Dz6StudentListActivity::class.java)
        startActivity(intent)
    }

    internal inner class HttpFormatException : Exception()
}