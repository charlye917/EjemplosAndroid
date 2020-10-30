package com.charlye934.firstproyectreactive

import java.util.*

data class Empleado(
    var id: Int,
    var nombre: String,
    var puesto: String,
    var antiguedad: Date,
    var salrio: Double,
    var plusSalario: Double
){
    companion object {
        fun setUpEmpleados(): List<Empleado> {
            val empleados: MutableList<Empleado> = ArrayList()
            empleados.add(Empleado(1, "Alberto", "Developer", Date(), 2000.00, 100.00))
            empleados.add(Empleado(2, "Maria", "CEO", Date(), 20000.00, 100.00))
            empleados.add(Empleado(3, "Luis", "Marketing", Date(), 1000.00, 100.00))
            empleados.add(Empleado(4, "Pablo", "CTO", Date(), 10000.00, 100.00))
            empleados.add(Empleado(5, "Marta", "Developer", Date(), 2000.00, 100.00))
            return empleados
        }
    }
}
