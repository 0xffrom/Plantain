package github.xffrom.plantain.root

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.runtime.Navigator
import dagger.hilt.android.components.ActivityComponent

@Composable
@CircuitInject(RootScreen::class, ActivityComponent::class)
internal fun RootPresenter(navigator: Navigator): RootScreen.State {
    return RootScreen.State("World") { event ->
        when (event) {
            is RootScreen.Event.NestedNav -> navigator.onNavEvent(event.navEvent)
            RootScreen.Event.BackClicked -> TODO()
        }
    }
}
