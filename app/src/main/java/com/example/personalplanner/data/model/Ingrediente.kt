package com.example.personalplanner.data.model

import android.os.Parcel
import android.os.Parcelable

data class Ingrediente(
    var nombre: String,
    var cantidad: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(cantidad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingrediente> {
        override fun createFromParcel(parcel: Parcel): Ingrediente {
            return Ingrediente(parcel)
        }

        override fun newArray(size: Int): Array<Ingrediente?> {
            return arrayOfNulls(size)
        }
    }
}
