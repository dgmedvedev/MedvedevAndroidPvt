package by.itacademy.pvt.dz8

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.pvt.BuildConfig
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Singleton
import by.itacademy.pvt.dz6.Student
import kotlinx.android.synthetic.main.fragment_edit_student_dz8.view.*

class Dz8StudentEditFragment : Fragment() {

    private lateinit var ageEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var urlEditText: EditText

    private val pattern = Patterns.WEB_URL

    private var listener: Listener? = null

    private lateinit var save: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(id: Long = -1L): Dz8StudentEditFragment {
            val fragment = Dz8StudentEditFragment()
            val bundle = Bundle()
            bundle.putLong(ID_STUDENT, id)

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_edit_student_dz8, container, false)

        val idStudent = arguments?.getLong(ID_STUDENT, -1)

        if (idStudent != null) {
            val user: Student? = Singleton.getStudentById(idStudent)

            nameEditText = view.findViewById(R.id.nameEditText)
            urlEditText = view.findViewById(R.id.urlEditText)
            ageEditText = view.findViewById(R.id.ageEditText)

            save = view.save
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
                        context,
                        resources.getText(R.string.enter_age),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
            listener?.startDz8StudentListFragment()
        } catch (hfe: HttpFormatException) {
            Toast.makeText(
                context,
                resources.getText(R.string.not_valid_url),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    internal inner class HttpFormatException : Exception()

    interface Listener {
        fun startDz8StudentListFragment()
    }
}