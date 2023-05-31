package com.example.personalplanner.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gastos_historial")
data class GastosHistorial(
    @PrimaryKey(autoGenerate = true) @NonNull val idRoom: Int,
    var mes: Mes,
    val mensaje: String
) {
    constructor(
        mes: Mes,
        mensaje: String
    ) : this(0, mes, mensaje)
}