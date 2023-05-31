package com.example.personalplanner.data.dao

import androidx.room.*
import com.example.personalplanner.data.model.Comida

@Dao
interface ComidaDao {
    @Insert
    fun insert(comidas: List<Comida>)

    @Update
    fun update(comida: Comida?)

    @Delete
    fun delete(comida: Comida?)

    @Query("DELETE FROM food")
    fun deleteAll()

    @Query("SELECT * FROM food")
    fun select(): List<Comida>

    @Query("SELECT * FROM food WHERE dificultad <= :dificultad AND tiempo <= :tiempo")
    fun selectFiltered(dificultad: Float, tiempo: Int): List<Comida>
}