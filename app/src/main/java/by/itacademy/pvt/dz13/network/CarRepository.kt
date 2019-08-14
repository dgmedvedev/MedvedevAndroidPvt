package by.itacademy.pvt.dz13.network

import by.itacademy.pvt.dz9.entity.CarResponse
import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi
import io.reactivex.Single

interface CarRepository {
    fun getCarByCoordinate(params: CoordinateParams, listener: CarRepositoryResult)
    fun getCars(params: CoordinateParams): Single<CarResponse>
}

interface CarRepositoryResult {
    fun onSuccess(data: List<Poi>)
    fun onError(throwable: Throwable)
}