package com.example.myapplication
import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class MyGLSurfaceView(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {
    private val mRender = ScreenProtectRender()

    fun init(){
        setEGLContextClientVersion(3)
        setEGLConfigChooser(true)
        setRenderer(mRender)
        renderMode = RENDERMODE_CONTINUOUSLY
    }


}