package com.example.personalplanner.data.repository

import com.example.personalplanner.data.PersonalPlannerDatabase
import com.example.personalplanner.data.dao.GastosHistorialDao
import com.example.personalplanner.data.model.GastosHistorial
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GastosHistorialRepository {

    private var gastosHistorialDao: GastosHistorialDao? = null
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        gastosHistorialDao = PersonalPlannerDatabase.dataBase?.gastosHistorialDao()
    }

    suspend fun deleteAllGastosHistorial() {
        return withContext(Dispatchers.IO){
            gastosHistorialDao!!.deleteAll()
        }
    }

    suspend fun getAllGastosHistorial(): ArrayList<GastosHistorial> {
        return withContext(dispatcher) {
            gastosHistorialDao!!.select() as ArrayList<GastosHistorial>
        }
    }

    suspend fun insertAllGastosHistorialRoom(list: ArrayList<GastosHistorial>) {
        withContext(dispatcher) {
            PersonalPlannerDatabase.dataBase!!.gastosHistorialDao()?.insert(list)
        }
    }
}