package by.itacademy.pvt.dz13

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.pvt.dz13.network.CarRepository
import by.itacademy.pvt.dz13.network.provideCarRepository
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz13ViewModel : ViewModel() {

    val state: MutableLiveData<Dz13State> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Dz13State>() }
    val selectedItem: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Poi>() }

    private var disposable: Disposable? = null

    private val carRepository: CarRepository = provideCarRepository()
    private var poiList: List<Poi> = listOf()

    init {
        disposable = carRepository
            .getCars(CoordinateParams(Coordinate(2342.0, 342.0), Coordinate(3242.0, 3453.0)))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                poiList = it.poiList
                state.value = Dz13State.Data(poiList)
                Log.e("AAA", "load success")
            }, { throwable ->
                state.value = Dz13State.LoadFailed(throwable)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun clickItem(item: Poi) {
        selectedItem.value = item
    }
}