package com.example.personalplanner.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.personalplanner.data.converter.IngredienteConverter
import com.example.personalplanner.data.model.Comida
import com.example.personalplanner.data.dao.ComidaDao
import com.example.personalplanner.data.dao.GastosDao
import com.example.personalplanner.data.dao.GastosHistorialDao
import com.example.personalplanner.data.model.Gastos
import com.example.personalplanner.data.model.GastosHistorial

@Database(entities = [Comida::class, Gastos::class, GastosHistorial::class], version = 23)
@TypeConverters(IngredienteConverter::class)
abstract class PersonalPlannerDatabase : RoomDatabase() {

    abstract fun comidaDao(): ComidaDao?
    abstract fun gastosDao(): GastosDao?
    abstract fun gastosHistorialDao(): GastosHistorialDao?

    companion object {
        @Volatile
        var dataBase: PersonalPlannerDatabase? = null

        fun create(context: Context) {
            if (dataBase == null) {
                synchronized(PersonalPlannerDatabase::class.java) {
                    if (dataBase == null) {
                        dataBase = databaseBuilder(
                            context.applicationContext,
                            PersonalPlannerDatabase::class.java, "personalplannerdatabase"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
        }
    }
}