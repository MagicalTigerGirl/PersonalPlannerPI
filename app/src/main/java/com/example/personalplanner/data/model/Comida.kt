package com.example.personalplanner.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Comida(
    @PrimaryKey(autoGenerate = true) @NonNull val id: Int,
    var tipo: String,
    var nombre: String,
    var dificultad: Float,
    var tiempo: Int,
    var ingredientes: List<Ingrediente>,
    var descripcion: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readInt(),
        parcel.createTypedArrayList(Ingrediente) ?: listOf(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(tipo)
        parcel.writeString(nombre)
        parcel.writeFloat(dificultad)
        parcel.writeInt(tiempo)
        parcel.writeString(descripcion)
        parcel.writeTypedList(ingredientes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comida> {
        override fun createFromParcel(parcel: Parcel): Comida {
            return Comida(parcel)
        }

        override fun newArray(size: Int): Array<Comida?> {
            return arrayOfNulls(size)
        }
    }
}