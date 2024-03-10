package github.xffrom.plantain.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityComponent

@Composable
@CircuitInject(MainScreen::class, ActivityComponent::class)
internal fun MainContent(state: MainScreen.State, modifier: Modifier) {
  Box(modifier = modifier.fillMaxSize()) { Text(text = "Hello, ${state.name}!") }
}
