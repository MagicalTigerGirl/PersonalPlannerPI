package com.example.personalplanner.data.converter

import androidx.room.TypeConverter
import com.example.personalplanner.data.model.Ingrediente
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredienteConverter {
    @TypeConverter
    fun fromString(value: String): List<Ingrediente> {
        val listType = object : TypeToken<List<Ingrediente>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<Ingrediente>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
