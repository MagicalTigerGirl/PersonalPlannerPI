package com.example.personalplanner.data.dao

import androidx.room.*
import com.example.personalplanner.data.model.Comida
import com.example.personalplanner.data.model.Gastos

@Dao
interface GastosDao {
    @Insert
    fun insert(gastos: ArrayList<Gastos>)

    @Update
    fun update(gastos: ArrayList<Gastos>)

    @Delete
    fun delete(gastos: Gastos?)

    @Query("DELETE FROM gastos")
    fun deleteAll()

    @Query("SELECT * FROM gastos")
    fun select(): List<Gastos>
}