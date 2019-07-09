package by.itacademy.pvt.dz8

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Singleton
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.utils.loadRoundImage
import kotlinx.android.synthetic.main.activity_details_student_dz6.*

class Dz8StudentDetailsActivity : Activity() {

    private var idStudent = -1L

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getIntent(context: Context, idStudent: Long): Intent {
            val intent = Intent(context, Dz8StudentDetailsActivity::class.java)
            intent.putExtra(ID_STUDENT, idStudent)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_student_dz8)

        idStudent = intent.getLongExtra(ID_STUDENT, -1)
        val user: Student? = Singleton.getStudentById(idStudent)
        if (user == null) {
            Toast.makeText(
                this,
                resources.getText(R.string.id_not_found),
                Toast.LENGTH_SHORT
            ).show()
            this.finish()
        }

        user?.let {
            name_details_student.text = user.name
            age_details_student.text = user.age.toString()
            loadRoundImage(user.imageUrl, photo_details_student)
        }

        delete.setOnClickListener {

            Singleton.getListStudent().remove(user)
            startActivity(Dz8StudentListActivity.getIntent(this@Dz8StudentDetailsActivity, idStudent))
            this.finish()
        }
    }
}