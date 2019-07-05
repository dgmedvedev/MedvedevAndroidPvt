package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R

class Dz6StudentListActivity : Activity(), Dz6ListAdapter.ClickListener {
    private lateinit var recyclerView: RecyclerView
    private var adapter: Dz6ListAdapter? = null

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getIntent(context: Context, idStudent: Long): Intent {
            val intent = Intent(context, Dz6StudentListActivity::class.java)
            intent.putExtra(ID_STUDENT, idStudent)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student_dz6)

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false
        adapter = Dz6ListAdapter(Singleton.getListStudent(), this)

        findViewById<ImageButton>(R.id.add_button)
            .setOnClickListener {
                startDz6StudentEditActivity()
            }
    }

    override fun onItemClick(item: Student) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()

        startActivity(Dz6StudentDetailsActivity.getIntent(this@Dz6StudentListActivity, item.id))
    }

    override fun onStart() {
        super.onStart()
        recyclerView.adapter = adapter
    }

    private fun startDz6StudentEditActivity() {
        val intent = Intent(this, Dz6StudentEditActivity::class.java)
        startActivity(intent)
    }
}