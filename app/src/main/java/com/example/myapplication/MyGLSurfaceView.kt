package com.example.myapplication
import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class MyGLSurfaceView(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {

    fun init(render:Renderer){
        setEGLContextClientVersion(3)
        setEGLConfigChooser(true)
        setRenderer(render)
        renderMode = RENDERMODE_CONTINUOUSLY
    }


}