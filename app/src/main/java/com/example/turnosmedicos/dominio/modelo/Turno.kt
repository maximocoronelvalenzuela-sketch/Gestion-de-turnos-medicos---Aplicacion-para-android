package com.example.turnosmedicos.dominio.modelo

import java.time.LocalDateTime

class Turno(
    val id: String,
    val paciente: Paciente,
    val profesional: Profesional,
    val fechaHora: LocalDateTime,
    val estadoTurno: EstadoTurno = EstadoTurno.PROGRAMADO,
    val estadoResolucion: EstadoResolucion = EstadoResolucion.PENDIENTE
) {

    companion object {
        fun crear(
            paciente: Paciente,
            profesional: Profesional,
            fechaHora: LocalDateTime = LocalDateTime.now()
        ): Turno {
            return Turno(
                id = java.util.UUID.randomUUID().toString(),
                paciente = paciente,
                profesional = profesional,
                fechaHora = fechaHora
            )
        }
    }

    fun estaVencido(ahora: LocalDateTime = LocalDateTime.now()): Boolean {
        return fechaHora.isBefore(ahora)
    }

    fun puedeConfirmarse(): Boolean {
        return estadoTurno == EstadoTurno.PROGRAMADO
    }

    fun puedeCancelarse(): Boolean {
        return estadoTurno == EstadoTurno.PROGRAMADO || estadoTurno == EstadoTurno.CONFIRMADO
    }

    fun puedeResolverse(ahora: LocalDateTime = LocalDateTime.now()): Boolean {
        return estadoTurno == EstadoTurno.CONFIRMADO &&
                estadoResolucion == EstadoResolucion.PENDIENTE &&
                estaVencido(ahora)
    }
}