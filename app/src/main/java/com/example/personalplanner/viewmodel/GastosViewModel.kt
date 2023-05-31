package com.example.personalplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listafacturas.viewmodel.StateLiveDataList
import com.example.personalplanner.data.model.Gastos
import com.example.personalplanner.data.model.GastosHistorial
import com.example.personalplanner.data.repository.GastosHistorialRepository
import com.example.personalplanner.data.repository.GastosRepository
import com.example.personalplanner.domain.GetGastosUseCase
import kotlinx.coroutines.launch

class GastosViewModel(
    private val repository: GastosRepository,
) : ViewModel() {

    var liveDataList: StateLiveDataList<List<Gastos>> = StateLiveDataList()
    var liveDataListGastosHistorial: StateLiveDataList<List<GastosHistorial>> = StateLiveDataList()

    private var getGastosUseCase = GetGastosUseCase(repository)

    private val repositoryGastosHistorial: GastosHistorialRepository = GastosHistorialRepository()

    var list: List<Gastos> = emptyList()
    var listHistorial: ArrayList<GastosHistorial> = ArrayList()

    init {
        viewModelScope.launch {

            liveDataList.setLoading()
            liveDataListGastosHistorial.setLoading()

            list = getGastosUseCase()
            listHistorial = repositoryGastosHistorial.getAllGastosHistorial()

            if (list.isEmpty())
                liveDataList.setNoData()
            else {
                liveDataList.setSuccess(list)
            }

            if (listHistorial.isNotEmpty()) {
                liveDataList.setSuccess(list)
            }
        }
    }

    fun getDataList() {
        liveDataList.setLoading()

        viewModelScope.launch {

            if (list.isEmpty())
                liveDataList.setNoData()
            else
                liveDataList.setSuccess(list)
        }
    }

    fun getDataListGastosHistorial() {
        liveDataListGastosHistorial.setLoading()

        viewModelScope.launch {

            if (listHistorial.isEmpty())
                liveDataListGastosHistorial.setNoData()
            else
                liveDataListGastosHistorial.setSuccess(listHistorial)
        }
    }


    fun add(gastosHistorial: GastosHistorial) {
        listHistorial.add(gastosHistorial)
    }

    fun getHistorialList(): ArrayList<GastosHistorial> {
        return listHistorial
    }

    fun updateGastos(list: ArrayList<Gastos>) {
        viewModelScope.launch {
            repository.updateAllRoom(list)
        }
    }

    fun deleteHistorialGastos() {
        viewModelScope.launch {
            repositoryGastosHistorial.deleteAllGastosHistorial()
        }
    }

    fun insertHistorialGastos(list: ArrayList<GastosHistorial>) {
        viewModelScope.launch {
            repositoryGastosHistorial.insertAllGastosHistorialRoom(list)
        }
    }
}