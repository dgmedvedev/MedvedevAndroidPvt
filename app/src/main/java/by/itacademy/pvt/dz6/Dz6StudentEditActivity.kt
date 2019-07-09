package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import by.itacademy.pvt.BuildConfig
import by.itacademy.pvt.R
import kotlinx.android.synthetic.main.activity_edit_student_dz6.save

class Dz6StudentEditActivity : Activity() {

    private lateinit var ageEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var urlEditText: EditText

    private var idStudent = -1L

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

        idStudent = intent.getLongExtra(ID_STUDENT, -1)
        val user: Student? = Singleton.getStudentById(idStudent)

        nameEditText = findViewById(R.id.nameEditText)
        urlEditText = findViewById(R.id.urlEditText)
        ageEditText = findViewById(R.id.ageEditText)

        save.setOnClickListener {
            val id = System.currentTimeMillis()
            val name = nameEditText.text.toString()
            var url = urlEditText.text.toString()
            if (BuildConfig.DEBUG) {
                url = "https://clck.ru/Gx4Nd"
            }

            try {
                val age = ageEditText.text.toString().toInt()

                if (idStudent != -1L) {
                    Singleton.getListStudent().remove(user)
                    user?.let { addStudent(user.id, url, name, age) }
                } else addStudent(id, url, name, age)
            } catch (nfe: NumberFormatException) {
                Toast.makeText(
                    this,
                    resources.getText(R.string.enter_age),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun startDz6StudentListActivity() {
        val intent = Intent(this, Dz6StudentListActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    private fun addStudent(id: Long, imageUrl: String, name: String, age: Int) {
        try {
            if (!pattern.matcher(imageUrl).matches()) throw HttpFormatException()

            Singleton.getListStudent().add(
                Student(
                    id,
                    imageUrl,
                    if (name == "") {
                        resources.getString(R.string.anonymous)
                    } else name,
                    age
                )
            )
            startDz6StudentListActivity()
        } catch (hfe: HttpFormatException) {
            Toast.makeText(
                this,
                resources.getText(R.string.not_valid_url),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    internal inner class HttpFormatException : Exception()
}