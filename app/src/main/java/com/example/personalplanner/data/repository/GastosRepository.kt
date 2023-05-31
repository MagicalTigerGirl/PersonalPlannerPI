package com.example.personalplanner.data.repository

import com.example.personalplanner.data.PersonalPlannerDatabase
import com.example.personalplanner.data.dao.GastosDao
import com.example.personalplanner.data.model.Gastos
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GastosRepository {

    private var gastosDao: GastosDao? = null
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        gastosDao = PersonalPlannerDatabase.dataBase?.gastosDao()
    }

    suspend fun getAllGastos(): List<Gastos> {
        return withContext(dispatcher) {
            gastosDao!!.select()
        }
    }

    suspend fun insertAllRoom(list: ArrayList<Gastos>) {
        withContext(dispatcher) {
            PersonalPlannerDatabase.dataBase!!.gastosDao()?.insert(list)
        }
    }

    suspend fun updateAllRoom(list: ArrayList<Gastos>) {
        withContext(dispatcher) {
            PersonalPlannerDatabase.dataBase!!.gastosDao()?.update(list)
        }
    }

    suspend fun deleteAllRoom() {
        withContext(dispatcher) {
            PersonalPlannerDatabase.dataBase!!.gastosDao()?.deleteAll()
        }
    }
}