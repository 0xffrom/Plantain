package github.xffrom.plantain.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dagger.hilt.android.AndroidEntryPoint
import github.xffrom.plantain.ui.theme.PlantainTheme
import javax.inject.Inject

@AndroidEntryPoint
internal class RootActivity : ComponentActivity() {

    @Inject
    lateinit var circuit: Circuit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CircuitCompositionLocals(circuit) {
                PlantainTheme {
                    val backStack = rememberSaveableBackStack(root = RootScreen)
                    val navigator = rememberCircuitNavigator(backStack) {
                        // Do something when the root screen is popped, usually exiting the app
                    }

                    NavigableCircuitContent(navigator, backStack)
                }
            }
        }
    }
}



