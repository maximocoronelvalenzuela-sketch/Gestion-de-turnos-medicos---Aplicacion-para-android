package com.example.turnosmedicos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.turnosmedicos.dominio.usecase.CrearTurnoUseCase
import com.example.turnosmedicos.ui.pantallas.CrearTurnoScreen
import com.example.turnosmedicos.ui.theme.TurnosMedicosTheme
import com.example.turnosmedicos.viewmodel.TurnoViewModel
import com.example.turnosmedicos.data.repositorio.FakeTurnoRepositorio

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TurnosMedicosTheme {
                val viewModel : TurnoViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            // 1. Capa de Datos: Creamos una implementación CONCRETA del Repositorio.
                            //    Esta es la única parte que "sabe" que estamos usando una implementación en memoria.
                            val repositorio = FakeTurnoRepositorio()

                            // 2. Capa de Dominio: Creamos el UseCase y le pasamos el Repositorio que necesita.
                            val useCase = CrearTurnoUseCase(repositorio) // <-- ¡ERROR SOLUCIONADO!

                            // 3. Capa de Presentación: Creamos el ViewModel y le pasamos el UseCase que necesita.
                            return TurnoViewModel(useCase) as T
                        }
                    }
                )

                CrearTurnoScreen(
                    viewModel = viewModel,
                    alCrearTurnoExitosamente = {
                        // después navegamos
                    }
                )
            }
        }
    }
}