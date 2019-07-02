package by.itacademy.pvt.dz6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R

class Dz6ListAdapter(private val items: ArrayList<Student>, private val listener: ClickListener) :
        RecyclerView.Adapter<Dz6ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz6ListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dz6_students, parent, false)

        val holder = Dz6ListViewHolder(view)

        holder.itemView.setOnClickListener {
            listener.onItemClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Dz6ListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface ClickListener {
        fun onItemClick(item: Student)
    }
}