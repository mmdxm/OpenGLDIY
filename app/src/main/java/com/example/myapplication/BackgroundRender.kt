package com.example.myapplication
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class BackgroundRender :  GLSurfaceView.Renderer {
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(100.0f,100.0f,100.0f,1.0f)

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0,0,width,height)

    }

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glClearColor(100f,0f,0f,1.0f)

    }


}