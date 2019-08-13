package by.itacademy.pvt.dz13

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.pvt.dz9.CarRepository
import by.itacademy.pvt.dz9.CarRepositoryResult
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi
import by.itacademy.pvt.dz9.provideCarRepository

class Dz13ViewModel : ViewModel(), CarRepositoryResult {

    val state: MutableLiveData<Dz13State> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Dz13State>() }
    val selectedItem: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Poi>() }

    private val carRepository: CarRepository = provideCarRepository()
    private var poiList: List<Poi> = listOf()

    override fun onSuccess(data: List<Poi>) {
        state.value = Dz13State.Data(data)
        this.poiList = data
    }

    override fun onError(throwable: Throwable) {
        state.value = Dz13State.LoadFailed(throwable)
    }

    fun load() {
        if (state.value == null)
            carRepository
                .getCarByCoordinate(
                    CoordinateParams
                        (
                        Coordinate(2342.0, 342.0),
                        Coordinate(3242.0, 3453.0)
                    ), this
                )
    }

    fun clickItem(item: Poi) {
        selectedItem.value = item
    }
}