package by.itacademy.pvt.dz6

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.utils.loadRoundImage

class Dz6ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = itemView.findViewById<ImageView>(R.id.photo_student)
    private val textView = view.findViewById<TextView>(R.id.name_student)

    private val textViewID = view.findViewById<TextView>(R.id.id_student)

    fun bind(item: Student) {
        loadRoundImage(item.imageUrl, imageView)
        textView.text = item.name

        textViewID.text = item.id.toString()
    }
}