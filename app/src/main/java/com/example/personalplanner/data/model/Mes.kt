package com.example.personalplanner.data.model

enum class Mes(val numero: Int) {
    ENERO(0),
    FEBRERO(1),
    MARZO(2),
    ABRIL(3),
    MAYO(4),
    JUNIO(5),
    JULIO(6),
    AGOSTO(7),
    SEPTIEMBRE(8),
    OCTUBRE(9),
    NOVIEMBRE(10),
    DICIEMBRE(11);

    companion object {
        fun getListaMeses(): List<String> {
            return values().map { it.name }
        }
    }
}