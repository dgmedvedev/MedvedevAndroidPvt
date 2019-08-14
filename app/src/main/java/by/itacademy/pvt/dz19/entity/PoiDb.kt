package by.itacademy.pvt.dz19.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import by.itacademy.pvt.dz19.Converters
import com.google.gson.annotations.SerializedName

const val POI_TABLE_NAME = "PoiDb"

@Entity(tableName = POI_TABLE_NAME)
data class PoiDb(

    @PrimaryKey // конкретно объект id будет уникальным и поиск будет быстрее
    // @ColumnInfo(name = "jhgjhg") - в базе данных будет такое имя переменной id
    @SerializedName("id")
    val id: String,

    @Embedded // этот объект нужно разложить, т.е. будут записаны внутренности(долгота и ширина)
    @SerializedName("coordinate")
    val coordinate: CoordinateDb?,

    @SerializedName("fleetType")
    @TypeConverters(Converters::class)
    val fleetType: FleetTypeDb?,

    @SerializedName("heading")
    val heading: Double?
)