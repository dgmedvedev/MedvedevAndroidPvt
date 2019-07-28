package by.itacademy.pvt.dz11.mvp

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
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.utils.loadRoundImage
import kotlinx.android.synthetic.main.fragment_details_student_dz8.view.*

class Dz11StudentDetailsFragment : Fragment(), Dz11StudentDetailsView {

    private lateinit var presenter: Dz11StudentDetailsPresenter

    private var listener: Listener? = null

    private lateinit var name: TextView
    private lateinit var age: TextView
    private lateinit var photoStudent: ImageView
    private lateinit var delete: Button
    private lateinit var edit: Button

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(id: Long): Dz11StudentDetailsFragment {
            val fragment = Dz11StudentDetailsFragment()
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

        presenter = Dz11StudentDetailsPresenter(idStudent)
        presenter.setView(this)

        name = view.name_details_student
        age = view.age_details_student
        photoStudent = view.photo_details_student
        delete = view.delete
        edit = view.edit

        presenter.getStudentById()

        return view
    }

    override fun backStack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showStudent(student: Student) {
        name.text = student.name
        age.text = student.age.toString()
        loadRoundImage(student.imageUrl, photoStudent)

        delete.setOnClickListener {
            presenter.deleteButtonWasClicked()
            backStack()
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                listener?.startDz11StudentListFragment()
        }

        edit.setOnClickListener {
            listener?.onEditStudentClick(student.id)
        }
    }

    override fun onError() {
        Toast.makeText(
            context,
            resources.getText(R.string.id_not_found),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null

        presenter.detach()
    }

    interface Listener {
        fun onEditStudentClick(id: Long)
        fun startDz11StudentListFragment()
    }
}