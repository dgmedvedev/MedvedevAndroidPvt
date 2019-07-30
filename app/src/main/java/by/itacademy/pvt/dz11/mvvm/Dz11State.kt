package by.itacademy.pvt.dz11.mvvm

import by.itacademy.pvt.dz9.entity.Poi

sealed class Dz11State {

    class LoadFailed(val throwable: Throwable) : Dz11State()
    class Data(val list: List<Poi>) : Dz11State()
}