package com.example.turnosmedicos.dominio.usecase

import com.example.turnosmedicos.dominio.modelo.Turno
import com.example.turnosmedicos.dominio.repositorio.TurnoRepositorio
import java.time.LocalDateTime

class CrearTurnoUseCase(
    private val turnoRepositorio: TurnoRepositorio
) {

    operator fun invoke(turno: Turno){
        validarTurno(turno)
        turnoRepositorio.crearTurno(turno)
    }

    private fun validarTurno(turno: Turno) {
        require(turno.fechaHora.isAfter(LocalDateTime.now())){
            "No se puede crear un turno en el pasado."
        }
    }

}