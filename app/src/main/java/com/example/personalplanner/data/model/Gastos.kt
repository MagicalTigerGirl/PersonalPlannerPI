package com.example.personalplanner.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gastos")
data class Gastos(
    @PrimaryKey(autoGenerate = true) @NonNull val idRoom: Int,
    val id: Int,
    var mes: Mes,
    var importe: Float,
) {
    constructor(
        id: Int,
        mes: Mes,
        importe: Float
    ) : this(0, id, mes, importe)
}

