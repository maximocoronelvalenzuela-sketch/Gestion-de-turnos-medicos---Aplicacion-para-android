package com.example.turnosmedicos.dominio.modelo

import java.time.LocalDateTime

class Turno(
    val id: String,
    val pacienteId: String,
    val profesionalId: String,
    val fechaHora: LocalDateTime,
    val estadoTurno: EstadoTurno = EstadoTurno.PROGRAMADO,
    val estadoResolucion: EstadoResolucion = EstadoResolucion.PENDIENTE
) {

    fun estaVencido(now: LocalDateTime = LocalDateTime.now()): Boolean {
        return fechaHora.isBefore(now)
    }

    fun puedeConfirmarse(): Boolean {
        return estadoTurno == EstadoTurno.PROGRAMADO
    }

    fun puedeCancelarse(): Boolean {
        return estadoTurno == EstadoTurno.PROGRAMADO || estadoTurno == EstadoTurno.CONFIRMADO
    }

    fun puedeResolverse(now: LocalDateTime = LocalDateTime.now()): Boolean {
        return estadoTurno == EstadoTurno.CONFIRMADO &&
                estadoResolucion == EstadoResolucion.PENDIENTE &&
                estaVencido(now)
    }
}