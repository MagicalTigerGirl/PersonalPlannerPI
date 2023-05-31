package com.example.personalplanner.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalplanner.R
import com.example.personalplanner.data.model.GastosHistorial
import com.example.personalplanner.databinding.ItemGastosHistorialBinding

class GastosHistorialAdapter: RecyclerView.Adapter<GastosHistorialAdapter.ViewHolder>() {

    var gastosHistorial: ArrayList<GastosHistorial> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gastos_historial, parent, false))
    }

    override fun getItemCount(): Int {
        return gastosHistorial.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gastosHistorial.get(position))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ItemGastosHistorialBinding.bind(itemView)

        fun bind(gastosHistorial: GastosHistorial) {

            binding.tvMensaje.text = gastosHistorial.mes.name + ": " + gastosHistorial.mensaje
        }
    }

    fun update(data: List<GastosHistorial>?) {
        gastosHistorial.clear()
        gastosHistorial.addAll(data!!)

        notifyDataSetChanged()
    }
}