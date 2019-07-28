package by.itacademy.pvt.dz11.mvp

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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6ListAdapter
import by.itacademy.pvt.dz6.Student
import by.itacademy.pvt.dz8.AppPrefManager
import kotlinx.android.synthetic.main.fragment_list_student_dz8.view.*

class Dz11StudentListFragment : Fragment(), Dz6ListAdapter.ClickListener, Dz11StudentListView {

    private lateinit var presenter: Dz11StudentListPresenter

    private var adapter: Dz6ListAdapter? = null
    private var listener: Listener? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var prefsManager: AppPrefManager
    private lateinit var search: EditText
    private lateinit var add: ImageButton

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_student_dz8, container, false)

        presenter = Dz11StudentListPresenter()
        presenter.setView(this)

        add = view.add_button
        search = view.search

        prefsManager = AppPrefManager(requireContext())
        search.setText(prefsManager.getUserText())

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = false

        adapter = Dz6ListAdapter(emptyList(), this)

        presenter.load()
        presenter.search(search.text.toString())

        search.addTextChangedListener(object : TextWatcher {

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

        add.setOnClickListener {
            listener?.startDz11StudentEditFragment()
        }

        return view
    }

    override fun showList(listStudent: List<Student>) {
        adapter?.updateList(listStudent)
    }

    override fun onStart() {
        super.onStart()
        recyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        prefsManager.saveUserText(search.text.toString())
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
        fun startDz11StudentEditFragment()
        fun onStudentClick(id: Long)
    }
}