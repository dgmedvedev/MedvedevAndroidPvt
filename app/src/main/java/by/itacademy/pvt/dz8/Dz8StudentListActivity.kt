package by.itacademy.pvt.dz8

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6ListAdapter
import by.itacademy.pvt.dz6.Singleton
import by.itacademy.pvt.dz6.Student
import kotlinx.android.synthetic.main.activity_list_student_dz6.*

class Dz8StudentListActivity : Activity(), Dz6ListAdapter.ClickListener {
    private lateinit var recyclerView: RecyclerView
    private var adapter: Dz6ListAdapter? = null

    private lateinit var prefsManager: AppPrefManager
    private lateinit var editText: EditText

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        const val SHARED_PREFS_NAME = "SHARED_PREFS_NAME"
        const val TEXT_KEY = "TEXT_KEY"

        fun getIntent(context: Context, idStudent: Long): Intent {
            val intent = Intent(context, Dz8StudentListActivity::class.java)
            intent.putExtra(ID_STUDENT, idStudent)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student_dz8)

        prefsManager = AppPrefManager(this)
        editText = findViewById(R.id.search)
        editText.setText(prefsManager.getUserText())

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false

        adapter = Dz6ListAdapter(Singleton.getListStudent(), this)
        adapter?.updateList(Singleton.filter(editText.text.toString()))

        search.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                adapter?.updateList(Singleton.filter(p0.toString()))
            }
        })
    }

    override fun onStart() {
        super.onStart()
        recyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        prefsManager.saveUserText(editText.text.toString())
    }

    override fun onItemClick(item: Student) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
        startActivity(Dz8StudentDetailsActivity.getIntent(this@Dz8StudentListActivity, item.id))
    }
}