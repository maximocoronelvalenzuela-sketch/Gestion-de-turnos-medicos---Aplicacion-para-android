package com.example.turnosmedicos.ui.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turnosmedicos.dominio.modelo.Especialidad
import com.example.turnosmedicos.dominio.modelo.Paciente
import com.example.turnosmedicos.dominio.modelo.Profesional
import com.example.turnosmedicos.dominio.modelo.Turno
import com.example.turnosmedicos.viewmodel.TurnoViewModel

@Composable
fun CrearTurnoScreen(
    viewModel: TurnoViewModel,
    alCrearTurnoExitosamente: () -> Unit
) {
    val estadoPantalla by viewModel.estadoPantalla.collectAsState()

    var nombrePaciente by remember { mutableStateOf("") }
    var nombreProfesional by remember { mutableStateOf("") }

    LaunchedEffect(estadoPantalla.turnoCreado) {
        if (estadoPantalla.turnoCreado) {
            alCrearTurnoExitosamente()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Crear turno",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nombrePaciente,
            onValueChange = { nombrePaciente = it },
            label = { Text("Paciente") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nombreProfesional,
            onValueChange = { nombreProfesional = it },
            label = { Text("Profesional") },
            modifier = Modifier.fillMaxWidth()
        )

        if (estadoPantalla.mensajeError != null) {
            Text(
                text = estadoPantalla.mensajeError!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                val paciente = Paciente(
                    id = "0",
                    nombre = nombrePaciente,
                    apellido = "Apellido demo",
                    dni = "00000000",
                    telefono = "0000000000"
                )

                val profesional = Profesional(
                    id = "0",
                    nombre = 
                        nombreProfesional,
                    apellido = "Apellido demo",
                    especialidad = Especialidad.OTRO,
                    matricula = "MAT-000",
                    telefono = "0000000000"
                )
                val turno = Turno.crear(
                    paciente = paciente,
                    profesional = profesional
                )
                viewModel.solicitarCreacionDeTurno(turno)
            },
            enabled = !estadoPantalla.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (estadoPantalla.cargando) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Guardar turno")
            }
        }
    }
}