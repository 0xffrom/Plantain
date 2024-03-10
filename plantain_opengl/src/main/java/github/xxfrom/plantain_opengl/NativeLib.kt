package github.xxfrom.plantain_opengl

object NativeLib {

    fun init() {
        System.loadLibrary("xxfrom")
    }

    external fun initOpenGL()

    external fun drawFrame()
}