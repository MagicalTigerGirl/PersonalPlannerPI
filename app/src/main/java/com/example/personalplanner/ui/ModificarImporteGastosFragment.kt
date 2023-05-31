package com.example.personalplanner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.listafacturas.viewmodel.StateDataList
import com.example.personalplanner.data.adapter.GastosHistorialAdapter
import com.example.personalplanner.data.model.Gastos
import com.example.personalplanner.data.model.GastosHistorial
import com.example.personalplanner.data.model.Mes
import com.example.personalplanner.databinding.FragmentModificarImporteGastosBinding
import com.example.personalplanner.viewmodel.GastosViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ModificarImporteGastosFragment : Fragment() {

    private var _binding: FragmentModificarImporteGastosBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModel<GastosViewModel>()
    private lateinit var adapterGastosHistorial: GastosHistorialAdapter
    var list: ArrayList<Gastos> = ArrayList()

    private lateinit var mes: Mes
    private var mesId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModificarImporteGastosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.liveDataList.observe(viewLifecycleOwner) {
            when (it.state) {
                StateDataList.DataState.SUCCESS -> {
                    list = (it.data as ArrayList<Gastos>?)!!
                }
                else -> {
                    StateDataList.DataState.ERROR
                }
            }
        }

        viewModel.liveDataListGastosHistorial.observe(viewLifecycleOwner) {
            when (it.state) {
                StateDataList.DataState.SUCCESS -> {
                    adapterGastosHistorial.update(it.data)
                }
                else -> {
                    StateDataList.DataState.ERROR
                }
            }
        }

        viewModel.getDataListGastosHistorial()

        val spinner: Spinner = binding.spMesesAnio
        val opciones = Mes.getListaMeses()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        binding.spMesesAnio.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
                mes = Mes.values()[i]
                mesId = mes.numero
                binding.tvImporte.text = list[mesId].importe.toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        binding.btnAnadir.setOnClickListener {
            val nuevoImporte: Float = (list.get(mesId).importe + Integer.parseInt(binding.tvImporteAAnadir.text.toString()))
            list[mesId].importe = nuevoImporte
            binding.tvImporte.text = nuevoImporte.toString()

            viewModel.add(GastosHistorial(mes, "Has gastado " + Integer.parseInt(binding.tvImporteAAnadir.text.toString())))
            adapterGastosHistorial.update(viewModel.getHistorialList())
            Toast.makeText(context, ""+ viewModel.getHistorialList().size, Toast.LENGTH_SHORT).show()
        }

        binding.btnEliminar.setOnClickListener {
            val nuevoImporte: Float = (list.get(mesId).importe - Integer.parseInt(binding.tvImporteAAnadir.text.toString()))
            list[mesId].importe = nuevoImporte
            binding.tvImporte.text = nuevoImporte.toString()
        }
    }

    private fun initRecyclerView() {
        adapterGastosHistorial = GastosHistorialAdapter()
        binding.rvGastosHistorial.adapter = adapterGastosHistorial
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.updateGastos(list)
        viewModel.deleteHistorialGastos()
        viewModel.insertHistorialGastos(viewModel.listHistorial)
    }
}