package by.itacademy.pvt.dz12.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Singleton
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.utils.loadRoundImage
import kotlinx.android.synthetic.main.fragment_details_student_dz8.view.*

class Dz12StudentDetails : Fragment() {

    private var listener: Listener? = null

    private lateinit var name: TextView
    private lateinit var age: TextView
    private lateinit var photoStudent: ImageView
    private lateinit var delete: Button
    private lateinit var edit: Button

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(id: Long): Dz12StudentDetails {
            val fragment = Dz12StudentDetails()
            val bundle = Bundle()
            bundle.putLong(ID_STUDENT, id)

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_details_student_dz8, container, false)

        val idStudent = arguments?.getLong(ID_STUDENT, -1)

        if (idStudent != null) {
            val user: Student? = Singleton.getStudentById(idStudent)

            if (user == null) {
                Toast.makeText(
                    context,
                    resources.getText(R.string.id_not_found),
                    Toast.LENGTH_SHORT
                ).show()
                activity?.supportFragmentManager?.popBackStack()
            } else {
                name = view.name_details_student
                age = view.age_details_student
                photoStudent = view.photo_details_student
                delete = view.delete
                edit = view.edit

                name.text = user.name
                age.text = user.age.toString()
                loadRoundImage(user.imageUrl, photoStudent)

                delete.setOnClickListener {
                    Singleton.getListStudent().remove(user)
                    activity?.supportFragmentManager?.popBackStack()
                    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                        listener?.startDz12StudentList()
                }

                edit.setOnClickListener {
                    listener?.onEditStudentClick(user.id)
                }
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface Listener {
        fun onEditStudentClick(id: Long)
        fun startDz12StudentList()
    }
}