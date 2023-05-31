package com.example.listafacturas.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalplanner.data.repository.ComidaRepository
import com.example.personalplanner.data.model.Comida
import com.example.listafacturas.domain.GetComidasUseCase
import com.example.personalplanner.data.model.ComidaResult
import kotlinx.coroutines.launch


class ComidaViewModel(private val repository: ComidaRepository) : ViewModel() {

    var liveDataList: StateLiveDataList<List<Comida>> = StateLiveDataList()
    private var getComidasUseCase = GetComidasUseCase(repository)

    var list: List<Comida> = emptyList()

    init {
        viewModelScope.launch {

            liveDataList.setLoading()

            list = getComidasUseCase()

            if (list.isEmpty())
                liveDataList.setNoData()
            else {
                liveDataList.setSuccess(list)
                tiempoMax = repository.tiempoMaximo
                tiempoMaxSelected = repository.tiempoMaximo
            }
        }
    }

    fun getDataList() {
        liveDataList.setLoading()

        viewModelScope.launch {
            if (isFiltered) {
                val filteredList: List<Comida> = repository.getListFiltered(dificultadSelected, tiempoMaxSelected)

                list = if (isChecked) {
                    filteredList.filter {
                        it.tipo.equals("ENTRANTE") && bEntrante.value == true ||
                                it.tipo.equals("BEBIDA") && bBebidas.value == true
                    }
                } else {
                    filteredList
                }
            }

            if (list.isEmpty())
                liveDataList.setNoData()
            else
                liveDataList.setSuccess(list)
        }
    }

    // SECONDFRAGMENT

    var isFiltered = false

    // RatingBar
    var dificultadSelected: Float = 5.0F

    // Seekbar
    var tiempoMax: Int = 0
    var tiempoMaxSelected: Int = 0

    // Checkbox

    var isChecked: Boolean = false

    var bEntrante: MutableLiveData<Boolean> = MutableLiveData()
    var bBebidas: MutableLiveData<Boolean> = MutableLiveData()
    var bPrincipal: MutableLiveData<Boolean> = MutableLiveData()
    var bSegundo: MutableLiveData<Boolean> = MutableLiveData()
    var bPostre: MutableLiveData<Boolean> = MutableLiveData()
}
