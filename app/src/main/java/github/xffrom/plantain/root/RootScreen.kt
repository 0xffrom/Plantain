package github.xffrom.plantain.root

import com.slack.circuit.foundation.NavEvent
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
internal data object RootScreen : Screen {
    data class State(
        val name: String,
        val eventSink: (event: Event) -> Unit,
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data object BackClicked : Event()

        data class NestedNav(val navEvent: NavEvent) : Event()
    }
}