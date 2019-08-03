package by.itacademy.pvt.dz12.mvp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz12.adapter.Dz12ListAdapter
import by.itacademy.pvt.dz12.Student
import by.itacademy.pvt.utils.AppPrefManager

class Dz12StudentListFragment : Fragment(), Dz12ListAdapter.ClickListener, Dz12StudentListView {

    private lateinit var presenter: Dz12StudentListPresenter

    private lateinit var progressBar: ProgressBar

    private var adapter: Dz12ListAdapter? = null
    private var listener: Listener? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var prefsManager: AppPrefManager
    private lateinit var searchEditText: EditText
    private lateinit var addImageButton: ImageButton

    private fun updateList() {
        presenter.search(searchEditText.text.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_student_dz12, container, false)

        presenter = Dz12StudentListPresenter()
        presenter.setView(this)

        progressBar = view.findViewById(R.id.progressBarDz12)

        addImageButton = view.findViewById(R.id.add_button)
        searchEditText = view.findViewById(R.id.search)

        prefsManager = AppPrefManager(requireContext())
        searchEditText.setText(prefsManager.getUserText())

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = false

        adapter = Dz12ListAdapter(emptyList(), this)

        presenter.load()
        updateList()

        searchEditText.addTextChangedListener(object : TextWatcher {

            var timer: Handler? = null
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                timer = Handler()
                timer?.postDelayed({
                    presenter.search(p0.toString())
                }, 500)
            }
        })

        addImageButton.setOnClickListener {
            listener?.startDz12StudentEditFragment()
        }

        return view
    }

    override fun showList(listStudent: List<Student>) {
        adapter?.updateList(listStudent)
    }

    override fun updateDatabase() {
        updateList()
    }

    override fun progressBarOn() {
        progressBar.visibility = View.VISIBLE
    }

    override fun progressBarOff() {
        progressBar.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        recyclerView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        recyclerView.adapter = adapter
        updateList()
    }

    override fun onStop() {
        super.onStop()
        prefsManager.saveUserText(searchEditText.text.toString())
    }

    override fun onItemClick(item: Student) {
        Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
        listener?.onStudentClick(item.id)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        presenter.detach()
    }

    interface Listener {
        fun startDz12StudentEditFragment()
        fun onStudentClick(id: String)
    }
}