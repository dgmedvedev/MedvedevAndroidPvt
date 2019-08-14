package by.itacademy.domain.repository

import by.itacademy.domain.entity.CoordinateParams
import by.itacademy.domain.entity.Poi
import io.reactivex.Single

interface CarRepository {
    fun getCars(params: CoordinateParams): Single<List<Poi>>
}