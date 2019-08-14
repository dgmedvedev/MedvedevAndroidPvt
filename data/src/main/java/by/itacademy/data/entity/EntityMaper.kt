package by.itacademy.data.entity

import by.itacademy.domain.entity.FleetType
import by.itacademy.domain.entity.Poi

fun PoiResponse.transform(): Poi = Poi(
    id = id,
    longitude = coordinate?.longitude ?: 0.0,
    latitude = coordinate?.latitude ?: 0.0,

    fleetType = if (fleetType != null) transformFleetType(fleetType) else null,
    heading = heading
)

fun transformFleetType(fleetType: FleetTypeResponse): FleetType =
    when (fleetType) {
        FleetTypeResponse.TAXI -> FleetType.TAXI
        FleetTypeResponse.POOLING -> FleetType.POOLING
    }