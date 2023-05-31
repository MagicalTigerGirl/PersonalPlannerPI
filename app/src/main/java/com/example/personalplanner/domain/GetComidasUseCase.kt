package com.example.listafacturas.domain

import com.example.personalplanner.data.model.Comida
import com.example.personalplanner.data.repository.ComidaRepository
import java.net.ConnectException
import java.net.SocketTimeoutException

class GetComidasUseCase(private val repository: ComidaRepository) {

    suspend operator fun invoke(): List<Comida> {

        var list: List<Comida>

        try {
            list = repository.getAllComidas()
        } catch (e: SocketTimeoutException) {
            list  = emptyList()
        }


        if(list.isNotEmpty()) {
            repository.deleteAllRoom()
            repository.insertAllRoom(list)
        } else {
            list = repository.getAllComidasMock()
        }

        repository.tiempoMaximo = list.stream().max(Comparator.comparing(Comida::tiempo)).get().tiempo

        return list
    }
}