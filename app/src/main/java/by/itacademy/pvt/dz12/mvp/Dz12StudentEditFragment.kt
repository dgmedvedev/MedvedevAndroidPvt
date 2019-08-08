package by.itacademy.pvt.dz12.mvp

import android.content.Context
import android.os.Bundle
import android.os.Handler
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

class Dz12StudentEditFragment : Fragment(), Dz12StudentEditView {

    private lateinit var presenter: Dz12StudentEditPresenter

    private lateinit var ageEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var urlEditText: EditText

    private var listener: Listener? = null

    private lateinit var saveButton: Button

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

        val defaultValue = resources.getString(R.string.default_value)
        val anonymous = resources.getString(R.string.anonymous)
        val urlDebug = resources.getString(R.string.url_debug)
        val notValidUrl = resources.getString(R.string.not_valid_url)
        val ageMustBeInteger = resources.getString(R.string.age_integer)

        val view = inflater.inflate(R.layout.fragment_edit_student_dz8, container, false)

        val idStudent = arguments?.getString(ID_STUDENT, defaultValue)

        if (idStudent == null)
            backStack()
        else {
            presenter = Dz12StudentEditPresenter(idStudent)
            presenter.setView(this)
        }

        nameEditText = view.findViewById(R.id.nameEditText)
        urlEditText = view.findViewById(R.id.urlEditText)
        ageEditText = view.findViewById(R.id.ageEditText)

        saveButton = view.findViewById(R.id.save)

        if (idStudent != defaultValue) {
            presenter.showStudentById()
        }

        saveButton.setOnClickListener {

            var name = nameEditText.text.toString()
            if (name == "")
                name = anonymous

            try {
                // если эти исключения обрабатывать в презентере,
                // выкидывает на страницу ListFragment и сообщение об ошибке
                // Обрабатываю здесь, чтобы на странице EditFragment просто
                // отображались сообщения об ошибке и пользователь оставался на ней,
                // пока не введет правильные значения или не нажмет Назад

                val age = ageEditText.text.toString().toInt()

                var url = urlEditText.text.toString()
                if (BuildConfig.DEBUG) {
                    url = urlDebug
                }

                presenter.saveStudent(url, name, age)
                val timer = Handler()
                timer.postDelayed({
                    listener?.startDz12StudentListFragment()
                }, 100)
            } catch (nfe: NumberFormatException) {
                onError(ageMustBeInteger)
            } catch (hfe: Dz12StudentEditPresenter.HttpFormatException) {
                onError(notValidUrl)
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
        presenter.detach()
    }

    interface Listener {
        fun startDz12StudentListFragment()
    }
}