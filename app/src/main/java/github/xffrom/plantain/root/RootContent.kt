package github.xffrom.plantain.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitContent
import dagger.hilt.android.components.ActivityComponent
import github.xffrom.plantain.main.MainScreen

@Composable
@CircuitInject(RootScreen::class, ActivityComponent::class)
internal fun RootContent(state: RootScreen.State, modifier: Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column {
            CircuitContent(
                modifier = Modifier
                    .weight(1f)
                    .padding(paddingValues),
                screen = MainScreen,
                onNavEvent = { navEvent -> state.eventSink(RootScreen.Event.NestedNav(navEvent)) },
            )
            BottomAppBar(modifier = Modifier) {
                IconButton(
                    onClick = {},
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = null)
                }
            }
        }
    }
}