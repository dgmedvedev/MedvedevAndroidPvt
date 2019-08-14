package by.itacademy.domain.entity

data class Poi(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val fleetType: FleetType?,
    val heading: Double?
)