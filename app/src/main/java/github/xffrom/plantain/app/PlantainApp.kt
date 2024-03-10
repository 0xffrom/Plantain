package github.xffrom.plantain.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import github.xxfrom.plantain_opengl.NativeLib

@HiltAndroidApp
internal class PlantainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        NativeLib.init()
    }
}
