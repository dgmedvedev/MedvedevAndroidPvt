package by.itacademy.pvt.dz19

import androidx.room.TypeConverter
import by.itacademy.pvt.dz19.entity.FleetTypeDb

class Converters {

    companion object {

        @TypeConverter
        @JvmStatic
        fun getFromFleetTypeDb(value: FleetTypeDb): String { // room сохранит в базу данных
            return value.name
        }

        @TypeConverter
        @JvmStatic
        fun getFromFleetTypeDb(value: String): FleetTypeDb { // берем из базы
            return FleetTypeDb.valueOf(value)
        }
    }
}