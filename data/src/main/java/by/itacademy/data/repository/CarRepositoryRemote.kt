package by.itacademy.data.repository

import by.itacademy.data.entity.transform
import by.itacademy.domain.repository.CarRepository
import by.itacademy.domain.entity.CoordinateParams
import by.itacademy.data.net.Api
import by.itacademy.data.net.NetProvider
import by.itacademy.domain.entity.Poi
import io.reactivex.Single

// CRUD - create, read, update, delete
class CarRepositoryRemote(private val api: Api = NetProvider.providePreparedApi()) : CarRepository {

    override fun getCars(params: CoordinateParams): Single<List<Poi>> {
        return api
            .getCarsByCoordinate(
                params.latitude1,
                params.longitude1,
                params.latitude2,
                params.longitude2
            )
            .map { carResponse ->
                carResponse.poiList.map { poi -> poi.transform() }
            }
    }
}