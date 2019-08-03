package by.itacademy.pvt.dz12.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz12.Student
import by.itacademy.pvt.utils.loadRoundImage

class Dz12ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = itemView.findViewById<ImageView>(R.id.photo_student)
    private val textView = itemView.findViewById<TextView>(R.id.name_student)

    fun bind(item: Student) {
        loadRoundImage(item.imageUrl, imageView)
        textView.text = item.name
    }
}