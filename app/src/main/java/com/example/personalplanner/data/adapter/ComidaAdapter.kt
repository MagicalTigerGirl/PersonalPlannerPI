package com.example.personalplanner.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalplanner.data.model.Comida
import com.example.personalplanner.R
import com.example.personalplanner.databinding.ItemFacturaBinding
import java.util.*

class ComidaAdapter(listener: OnManageFacturaListener): RecyclerView.Adapter<ComidaAdapter.ViewHolder>() {

    var comidas: ArrayList<Comida> = ArrayList()
    var listener: OnManageFacturaListener = listener

    interface OnManageFacturaListener {
        fun onShowFactura(comida: Comida)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_factura, parent, false))
    }

    override fun getItemCount(): Int {
        return comidas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comidas.get(position), listener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ItemFacturaBinding.bind(itemView)

        fun bind(comida: Comida, listener: OnManageFacturaListener) {

            binding.tvName.text = comida.nombre

            binding.cardView.setOnClickListener { listener.onShowFactura(comida) }
        }
    }

    fun update(data: List<Comida>?) {
        comidas.clear()
        comidas.addAll(data!!)

        notifyDataSetChanged()
    }
}