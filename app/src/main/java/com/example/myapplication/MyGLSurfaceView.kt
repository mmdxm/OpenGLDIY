package com.example.myapplication
import android.content.Context
import android.graphics.Canvas
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.KeyEvent

class MyGLSurfaceView(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {
    private val mRender = BackgroundRender()

    fun init(){
        mRender.setColor(1f,1f)
        setEGLContextClientVersion(3)
        setEGLConfigChooser(true)
        setRenderer(mRender)
        renderMode = RENDERMODE_CONTINUOUSLY
        setOnClickListener{
            queueEvent{
                mRender.setColor(g = 1f)
            }
        }
    }

}