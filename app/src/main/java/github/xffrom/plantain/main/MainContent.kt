package github.xffrom.plantain.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityComponent
import github.xffrom.plantain.MyGLSurfaceView

@Composable
@CircuitInject(MainScreen::class, ActivityComponent::class)
internal fun MainContent(state: MainScreen.State, modifier: Modifier) {
    AndroidView(modifier = modifier.fillMaxSize(), factory = {
        MyGLSurfaceView(it).apply {}
    })
}
