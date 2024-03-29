package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.itacademy.pvt.R
import by.itacademy.pvt.utils.loadRoundImage
import kotlinx.android.synthetic.main.activity_details_student_dz6.*

class Dz6StudentDetailsActivity : Activity() {

    private var idStudent = -1L

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getIntent(context: Context, idStudent: Long): Intent {
            val intent = Intent(context, Dz6StudentDetailsActivity::class.java)
            intent.putExtra(ID_STUDENT, idStudent)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_student_dz6)

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
            startActivity(Dz6StudentListActivity.getIntent(this@Dz6StudentDetailsActivity, idStudent))
            this.finish()
        }

        edit.setOnClickListener {
            startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentDetailsActivity, idStudent))
            this.finish()
        }
    }
}