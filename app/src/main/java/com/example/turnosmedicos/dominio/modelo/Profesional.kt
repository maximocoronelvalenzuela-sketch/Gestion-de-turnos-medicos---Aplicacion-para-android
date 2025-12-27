package com.example.turnosmedicos.dominio.modelo

class Profesional(
    val id: String,
    val nombre: String,
    val apellido: String,
    val especialidad: Especialidad,
    val matricula: String,
    val telefono: String,
    val email: String? = null,
    val activo: Boolean? = true
) {
}