package github.xffrom.plantain.main

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
internal data object MainScreen : Screen {
  data class State(val name: String) : CircuitUiState
}
