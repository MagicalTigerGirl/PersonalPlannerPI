package com.example.personalplanner.domain

import com.example.personalplanner.data.model.Comida
import com.example.personalplanner.data.model.Gastos
import com.example.personalplanner.data.model.Mes
import com.example.personalplanner.data.repository.GastosRepository

class GetGastosUseCase(private val repository: GastosRepository) {

    suspend operator fun invoke(): List<Gastos> {

        var list: ArrayList<Gastos> = repository.getAllGastos() as ArrayList<Gastos>

        if(list.isEmpty()) {
            list.add(Gastos(Mes.ENERO.numero, Mes.ENERO, 0f))
            list.add(Gastos(Mes.FEBRERO.numero, Mes.FEBRERO, 0f))
            list.add(Gastos(Mes.MARZO.numero, Mes.MARZO, 0f))
            list.add(Gastos(Mes.ABRIL.numero, Mes.ABRIL, 0f))
            list.add(Gastos(Mes.MAYO.numero, Mes.MAYO, 0f))
            list.add(Gastos(Mes.JUNIO.numero, Mes.JUNIO, 0f))
            list.add(Gastos(Mes.JULIO.numero, Mes.JULIO, 0f))
            list.add(Gastos(Mes.AGOSTO.numero, Mes.AGOSTO, 0f))
            list.add(Gastos(Mes.SEPTIEMBRE.numero, Mes.SEPTIEMBRE, 0f))
            list.add(Gastos(Mes.OCTUBRE.numero, Mes.OCTUBRE, 0f))
            list.add(Gastos(Mes.NOVIEMBRE.numero, Mes.NOVIEMBRE, 0f))
            list.add(Gastos(Mes.DICIEMBRE.numero, Mes.DICIEMBRE, 0f))

            repository.insertAllRoom(list)
        }

        return list
    }
}