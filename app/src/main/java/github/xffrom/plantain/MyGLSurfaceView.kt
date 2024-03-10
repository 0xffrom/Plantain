package github.xffrom.plantain

import android.content.Context
import android.opengl.GLSurfaceView
import github.xxfrom.plantain_opengl.NativeLib
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyGLSurfaceView(context: Context) : GLSurfaceView(context) {

    init {
        setEGLContextClientVersion(3)
        setRenderer(MyRenderer())
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}

class MyRenderer : GLSurfaceView.Renderer {
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        NativeLib.initOpenGL()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {

    }

    override fun onDrawFrame(gl: GL10?) {
        NativeLib.drawFrame()
    }
}
