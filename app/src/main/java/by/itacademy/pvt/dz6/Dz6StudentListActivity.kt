package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Intent
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

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = Dz6ListAdapter(Singleton.getListStudent(), this)

        findViewById<ImageButton>(R.id.add_button)
            .setOnClickListener {
                startDz6StudentEditActivity()
            }
    }

    override fun onItemClick(item: Student) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()

        //  val imageView = findViewById<ImageView>(R.id.photo_details_student)
        //  loadCirclePicture(this,R.drawable.ic_launcher_background,imageView)
        //  findViewById<TextView>(R.id.name_details_student).text = "NAME"
        //  findViewById<TextView>(R.id.age_details_student).text = item.age.toString()

        startDz6StudentDetailsActivity()
    }

    private fun startDz6StudentEditActivity() {
        val intent = Intent(this, Dz6StudentEditActivity::class.java)
        startActivity(intent)
    }

    private fun startDz6StudentDetailsActivity() {
        val intent = Intent(this, Dz6StudentDetailsActivity::class.java)
        startActivity(intent)
    }
}