package by.itacademy.pvt.dz6

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R

class Dz6ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = view.findViewById<ImageView>(R.id.photo_student)
    private val textView = view.findViewById<TextView>(R.id.name_student)

    fun bind(item: Student) {
        imageView.setImageResource(R.drawable.ic_launcher_background)
        textView.text = item.name
    }
}