package by.itacademy.domain.usecase

import by.itacademy.domain.entity.CoordinateParams
import by.itacademy.domain.entity.Poi
import by.itacademy.domain.repository.CarRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetCarHamburgUseCase(
    private val repository: CarRepository,
    private val postSchedulers: Scheduler
) {

    private val params = CoordinateParams(
        3.4,
        4.4,
        3.4,
        4.4
    )

    fun get(): Single<List<Poi>> {
        return repository
            .getCars(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postSchedulers)
    }
}