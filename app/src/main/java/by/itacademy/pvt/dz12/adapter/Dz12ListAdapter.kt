package by.itacademy.pvt.dz12.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz12.Student

class Dz12ListAdapter(private var items: List<Student>, private val listener: ClickListener) :
    RecyclerView.Adapter<Dz12ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz12ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dz6_students, parent, false)

        val holder = Dz12ListViewHolder(view)

        holder.itemView.setOnClickListener {
            listener.onItemClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Dz12ListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateList(filterList: List<Student>) {
        items = filterList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClick(item: Student)
    }
}