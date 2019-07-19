package by.itacademy.pvt.dz9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import by.itacademy.pvt.R
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.dz9.entity.Poi

class Dz9Adapter(private var items: List<Poi>, private var listener: ClickListener) :
    RecyclerView.Adapter<Dz9CarViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz9CarViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_dz9, parent, false)

        val holder = Dz9CarViewHolder(layoutInflater)
        holder.itemView.setOnClickListener {
            listener.onCarClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz9CarViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface ClickListener {
        fun onCarClick(item: Poi)
    }
}

class Dz9CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val fleetType = view.findViewById<TextView>(R.id.dz9FleetType)
    private val coordinateLatitude = view.findViewById<TextView>(R.id.dz9CoordLat)
    private val coordinateLongitude = view.findViewById<TextView>(R.id.dz9CoordLng)

    fun bind(item: Poi) {
        fleetType.text = item.fleetType?.name
        coordinateLatitude.text = item.coordinate?.latitude.toString()
        coordinateLongitude.text = item.coordinate?.longitude.toString()
    }
}