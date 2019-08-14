package by.itacademy.pvt.dz19.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import by.itacademy.pvt.dz19.entity.PoiDb
import by.itacademy.pvt.dz19.entity.POI_TABLE_NAME

@Dao
interface PoiDao { // одна dao на одно entity

    @Insert(onConflict = OnConflictStrategy.REPLACE) // как решать конфликты (например: создаем машину, а она уже есть)
    fun insert(poi: PoiDb)

    @Query("SELECT * FROM $POI_TABLE_NAME") // пишем как эти данные получить
    // (пишем SELECT и названия калонок которые нужно вытянуть, если нужны все - ставим *)
    // FROM из какой таблицы данные
    fun get(): List<PoiDb>

    @Query("SELECT * FROM $POI_TABLE_NAME WHERE id = :id LIMIT 1") // LIMIT - найдет id и сразу вернет 1 запись
    fun getById(id: String): PoiDb

    @Query("DELETE FROM $POI_TABLE_NAME")
    fun delete()

    //   @Query("DELETE FROM $POI_TABLE_NAME WHERE id = :id LIMIT 1")
    //   fun deleteById(id: String)
}
/*
    SELECT * FROM student INNER JOIN country ON student.id_country = country.id
    в INNER JOIN если не нашел удаляет
    1 таблица            прикрепляем 2 таблицу
    INNER JOIN - объединение таблиц (можно объединять много таблиц сразу)
    ON - по каким полям делать объединение

    SELECT * FROM student LEFT JOIN country ON student.id_country = country.id
    берет все что слева, а справа буде null

    SELECT * FROM student RIGHT JOIN country ON student.id_country = country.id
    берет все что справа, слева будет null

    SELECT * FROM student OUTER JOIN country ON student.id_country = country.id

    SELECT * FROM student CROSS JOIN country ON student.id_country = country.id

 */