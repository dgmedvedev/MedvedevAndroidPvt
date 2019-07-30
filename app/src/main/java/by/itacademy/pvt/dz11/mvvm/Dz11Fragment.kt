package by.itacademy.pvt.dz11.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.Dz9Adapter
import by.itacademy.pvt.dz9.entity.Poi

class Dz11Fragment : Fragment(), Dz9Adapter.ClickListener {

    private lateinit var viewModel: Dz11ViewModel
    private lateinit var adapter: Dz9Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_car_dz9, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this.activity!!).get(Dz11ViewModel::class.java)
        viewModel.state.observe(this, Observer {
            when (it) {
                is Dz11State.Data -> {
                    filledAdapter(it.list)
                }
            }
        })
    }

    override fun onCarClick(item: Poi) {
        viewModel.clickItem(item)
    }

    private fun filledAdapter(listPoi: List<Poi>) {
        val dz9RecyclerView = view!!.findViewById<RecyclerView>(R.id.dz9RecyclerView)
        dz9RecyclerView.setHasFixedSize(true)
        dz9RecyclerView.layoutManager = LinearLayoutManager(context)
        dz9RecyclerView.isNestedScrollingEnabled = false
        adapter = Dz9Adapter(listPoi, this@Dz11Fragment)
        dz9RecyclerView.adapter = adapter
    }
}