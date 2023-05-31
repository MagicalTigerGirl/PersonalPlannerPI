package com.example.personalplanner.data.repository

import com.example.personalplanner.data.model.Comida
import com.example.personalplanner.core.PersonalPlannerClient
import com.example.personalplanner.data.PersonalPlannerDatabase
import com.example.personalplanner.data.dao.ComidaDao
import com.example.personalplanner.data.model.ComidaResult
import com.example.personalplanner.ui.application.MainApplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ComidaRepository(private val client: PersonalPlannerClient) {

    private var comidaDao: ComidaDao? = null
    var tiempoMaximo: Int = 0
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        comidaDao = PersonalPlannerDatabase.dataBase?.comidaDao()
    }

    suspend fun getAllComidasMock(): List<Comida> {
        return withContext(Dispatchers.IO) {
            client.serviceMock.listFacturas().body()?.comidas ?: emptyList()
        }

    }

    suspend fun getAllComidas(): List<Comida> {
        return withContext(Dispatchers.IO) {
            val result: List<Comida> = client.service.listFacturas().body()?.comidas ?: emptyList()

            result
        }

    }

    suspend fun getAllComidasRoom(): List<Comida> {
        return withContext(dispatcher) {
            comidaDao!!.select()
        }

    }

    suspend fun insertAllRoom(list: List<Comida>) {
        withContext(dispatcher) {
            PersonalPlannerDatabase.dataBase!!.comidaDao()?.insert(list)
        }
    }

    suspend fun deleteAllRoom() {
        withContext(dispatcher) {
            PersonalPlannerDatabase.dataBase!!.comidaDao()?.deleteAll()
        }
    }

    suspend fun getListFiltered(dificultad: Float, tiempo: Int): List<Comida> {
        return withContext(dispatcher) {
            val list: List<Comida>? = PersonalPlannerDatabase.dataBase!!.comidaDao()?.selectFiltered(dificultad, tiempo)
            list ?: emptyList()
        }
    }
}