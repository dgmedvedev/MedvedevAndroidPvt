package by.itacademy.pvt.dz19.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.itacademy.pvt.dz19.Converters
import by.itacademy.pvt.dz19.dao.PoiDao

@Database(entities = [PoiDb::class], version = 1)
// указываем все таблицы, которые будут использоваться
// и версия базы данных
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() { // класс отвечает за общую настройку базы данных

    companion object {

        var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "myDataBase")
                    .fallbackToDestructiveMigration().build()
                // указываем, что делать во время миграции. Здесь удаляем старую и создаем новую
            }
            return instance!!
        }
    }

    abstract fun getPoiDao(): PoiDao
}