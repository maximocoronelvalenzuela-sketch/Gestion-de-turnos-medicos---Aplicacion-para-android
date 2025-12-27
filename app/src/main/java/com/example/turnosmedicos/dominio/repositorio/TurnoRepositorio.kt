package com.example.turnosmedicos.dominio.repositorio

import com.example.turnosmedicos.dominio.modelo.Turno

interface TurnoRepositorio {
    fun crearTurno(turno: Turno)
}