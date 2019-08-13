package by.itacademy.pvt.dz13

import by.itacademy.pvt.dz9.entity.Poi

sealed class Dz13State {

    class LoadFailed(val throwable: Throwable) : Dz13State()
    class Data(val list: List<Poi>) : Dz13State()
}