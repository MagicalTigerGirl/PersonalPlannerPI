package com.example.personalplanner.data.dao

import androidx.room.*
import com.example.personalplanner.data.model.GastosHistorial
import com.example.personalplanner.data.model.Mes

@Dao
interface GastosHistorialDao {
    @Insert
    fun insert(gastosHistorial: List<GastosHistorial>)

    @Update
    fun update(gastosHistorial: GastosHistorial?)

    @Delete
    fun delete(gastosHistorial: GastosHistorial?)

    @Query("DELETE FROM gastos_historial")
    fun deleteAll()

    @Query("SELECT * FROM gastos_historial")
    fun select(): List<GastosHistorial>
}