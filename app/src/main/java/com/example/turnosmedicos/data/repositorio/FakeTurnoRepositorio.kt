package com.example.turnosmedicos.data.repositorio

import com.example.turnosmedicos.dominio.modelo.Turno
import com.example.turnosmedicos.dominio.repositorio.TurnoRepositorio

class FakeTurnoRepositorio : TurnoRepositorio {
    private val turnos = mutableListOf<Turno>()

    override fun crearTurno(turno: Turno) {
        turnos.add(turno)
    }

    fun obtenerTurnos(): List<Turno> = turnos
}