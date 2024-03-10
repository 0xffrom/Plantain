package github.xffrom.plantain.main

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import dagger.hilt.android.components.ActivityComponent

@Composable
@CircuitInject(MainScreen::class, ActivityComponent::class)
internal fun MainPresenter(navigator: Navigator): MainScreen.State {
  return MainScreen.State("World")
}
