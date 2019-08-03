package by.itacademy.pvt.dz12.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.pvt.BuildConfig
import by.itacademy.pvt.R
import by.itacademy.pvt.dz12.Student
import kotlinx.android.synthetic.main.fragment_edit_student_dz8.view.*

class Dz12StudentEditFragment : Fragment(), Dz12StudentEditView {

    private lateinit var presenter: Dz12StudentEditPresenter

    private lateinit var ageEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var urlEditText: EditText

    private var listener: Listener? = null

    private lateinit var save: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(id: String = "-1"): Dz12StudentEditFragment {
            val fragment = Dz12StudentEditFragment()
            val bundle = Bundle()
            bundle.putString(ID_STUDENT, id)

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_edit_student_dz8, container, false)

        val idStudent = arguments?.getString(ID_STUDENT, "-1")

        val anonymous: String = resources.getString(R.string.anonymous)

        if (idStudent == null)
            backStack()
        else {
            presenter = Dz12StudentEditPresenter(idStudent)
            presenter.setView(this)
        }

        nameEditText = view.findViewById(R.id.nameEditText)
        urlEditText = view.findViewById(R.id.urlEditText)
        ageEditText = view.findViewById(R.id.ageEditText)

        save = view.save

        if (idStudent != "-1") {
            presenter.getStudentById()
        }

        save.setOnClickListener {

            var name = nameEditText.text.toString()
            if (name == "")
                name = anonymous

            try {
                val age = ageEditText.text.toString().toInt()

                var url = urlEditText.text.toString()
                if (BuildConfig.DEBUG) {
                    url = "https://clck.ru/Gx4Nd"
                }

                presenter.saveStudent(url, name, age)
                listener?.startDz12StudentListFragment()
            } catch (nfe: NumberFormatException) {
                onError("Age must be an integer")
            } catch (hfe: Dz12StudentEditPresenter.HttpFormatException) {
                onError("Not valid URL")
            }
        }
        return view
    }

    override fun backStack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showStudent(student: Student) {
        nameEditText.setText(student.name)
        ageEditText.setText(student.age.toString())
        urlEditText.setText(student.imageUrl)
    }

    override fun onError(error: String) {
        Toast.makeText(
            context,
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null

        presenter.onDestroy()
    }

    interface Listener {
        fun startDz12StudentListFragment()
    }
}