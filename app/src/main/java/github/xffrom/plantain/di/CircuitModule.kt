package github.xffrom.plantain.di

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.Multibinds

@Module
@InstallIn(ActivityComponent::class)
interface CircuitModule {

  @Multibinds
  fun presenterFactories(): Set<Presenter.Factory>

  @Multibinds
  fun viewFactories(): Set<Ui.Factory>

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