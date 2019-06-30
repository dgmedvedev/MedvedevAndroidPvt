package by.itacademy.pvt.dz6

import android.app.Activity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R

class Dz6StudentListActivity : Activity(), Dz6ListAdapter.ClickListener {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student_dz6)

        findViewById<ImageButton>(R.id.add_button)
            .setOnClickListener {
            }

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false

        val items = listOf(
            Student(1, "", "Макс", 20),
            Student(2, "", "Дима", 21),
            Student(3, "", "Макс", 22),
            Student(4, "", "Дима", 23),
            Student(5, "", "Макс", 24),
            Student(6, "", "Дима", 25),
            Student(7, "", "Макс", 23),
            Student(8, "", "Дима", 24),
            Student(9, "", "Макс", 25)
        )

        recyclerView.adapter = Dz6ListAdapter(items, this)
    }

    override fun onItemClick(item: Student) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
    }
}