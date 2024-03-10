package github.xffrom.plantain.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.foundation.NavEvent
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.Multibinds
import github.xffrom.plantain.main.MainScreen
import github.xffrom.plantain.ui.theme.PlantainTheme
import javax.inject.Inject
import kotlinx.parcelize.Parcelize

@Module
@InstallIn(ActivityComponent::class)
interface CircuitModule {

  @Multibinds fun presenterFactories(): Set<Presenter.Factory>

  @Multibinds fun viewFactories(): Set<Ui.Factory>

  companion object {
    @Provides
    fun provideCircuit(
        uiFactories: Set<@JvmSuppressWildcards Ui.Factory>,
        presenterFactories: Set<@JvmSuppressWildcards Presenter.Factory>,
    ): Circuit {
      return Circuit.Builder()
          .addUiFactories(uiFactories)
          .addPresenterFactories(presenterFactories)
          .build()
    }
  }
}

@AndroidEntryPoint
class RootActivity : ComponentActivity() {

  @Inject lateinit var circuit: Circuit

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      CircuitCompositionLocals(circuit) {
        PlantainTheme {
          val backStack = rememberSaveableBackStack(root = RootScreen)
          val navigator =
              rememberCircuitNavigator(backStack) {
                // Do something when the root screen is popped, usually exiting the app
              }

          NavigableCircuitContent(navigator, backStack)
        }
      }
    }
  }
}

@Parcelize
data object RootScreen : Screen {
  data class State(
      val name: String,
      val eventSink: (event: Event) -> Unit,
  ) : CircuitUiState

  sealed class Event : CircuitUiEvent {
    data object BackClicked : Event()

    data class NestedNav(val navEvent: NavEvent) : Event()
  }
}

@Composable
@CircuitInject(RootScreen::class, ActivityComponent::class)
fun RootContent(state: RootScreen.State, modifier: Modifier) {
  Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
    Column {
      CircuitContent(
          modifier = Modifier.weight(1f).padding(paddingValues),
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

@Composable
@CircuitInject(RootScreen::class, ActivityComponent::class)
fun RootPresenter(navigator: Navigator): RootScreen.State {
  return RootScreen.State("World") { event ->
    when (event) {
      is RootScreen.Event.NestedNav -> navigator.onNavEvent(event.navEvent)
      RootScreen.Event.BackClicked -> TODO()
    }
  }
}
