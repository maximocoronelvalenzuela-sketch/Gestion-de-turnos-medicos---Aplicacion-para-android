package com.example.turnosmedicos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.turnosmedicos.dominio.modelo.Turno
import com.example.turnosmedicos.dominio.usecase.CrearTurnoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class EstadoPantallaTurno(
    val cargando: Boolean = false,
    val mensajeError: String? = null,
    val turnoCreado: Boolean = false
)

class TurnoViewModel(
    private val crearTurno: CrearTurnoUseCase
) : ViewModel() {

    private val estadoInternoPantalla = MutableStateFlow(EstadoPantallaTurno())
    val estadoPantalla: StateFlow<EstadoPantallaTurno> = estadoInternoPantalla

    fun solicitarCreacionDeTurno(turno: Turno) {
        estadoInternoPantalla.update {
            it.copy(cargando = true, mensajeError = null)
        }

        try {
            crearTurno(turno)

            estadoInternoPantalla.update {
                it.copy(cargando = false, turnoCreado = true)
            }
        } catch (error: IllegalArgumentException) {
            estadoInternoPantalla.update {
                it.copy(cargando = false, mensajeError = error.message)
            }
        }
    }
}