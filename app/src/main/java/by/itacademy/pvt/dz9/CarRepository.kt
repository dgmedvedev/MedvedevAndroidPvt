package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi

interface CarRepository {

    fun getCarByCoordinate(params: CoordinateParams, listener: CarRepositoryResult) // добавить дома, params: CoordinateParams,listener:
}

interface CarRepositoryResult {
    fun onSuccess(data: List<Poi>)
    fun onError(throwable: Throwable)
}