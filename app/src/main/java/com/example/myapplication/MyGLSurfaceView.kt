package com.example.myapplication
import android.content.Context
import android.graphics.Canvas
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.KeyEvent

class MyGLSurfaceView(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {
    private val mRender = BackgroundRender()

    fun init(){
        mRender.setColor(1f)
        setEGLContextClientVersion(3)
        setEGLConfigChooser(true)
        setRenderer(mRender)
        renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        //requestRender()
        setOnClickListener{
            queueEvent{
                mRender.setColor(g = 1f)
            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

}