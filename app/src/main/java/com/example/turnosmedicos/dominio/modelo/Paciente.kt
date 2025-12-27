package com.example.turnosmedicos.dominio.modelo

import java.time.LocalDate

class Paciente(
    val id: String,
    val nombre: String,
    val apellido: String,
    val dni: String,
    val telefono: String,
    val email: String? = null,
    val fechaNacimiento: LocalDate? = null,
    val activo: Boolean? = true
) {
}