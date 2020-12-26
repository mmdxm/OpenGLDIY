package com.example.myapplication
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.util.Log
import java.util.Random
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class BackgroundRender :  GLSurfaceView.Renderer {
    companion object{
        const val TAG = "BackgroundRender"
    }
    private var mRed :Float = 0f
    private var mGreen :Float = 0f
    private var mBlue:Float = 0f

    private var count:Long  = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0f,0f,0f,1f)
        gl?.glClear(GL10.GL_COLOR_BUFFER_BIT or  GL10.GL_DEPTH_BUFFER_BIT)

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        gl?.glViewport(0, 0, width, height)
        // for a fixed camera, set the projection too
        val  ratio = width.toFloat() / height.toFloat()
        gl?.glMatrixMode(GL10.GL_PROJECTION)
        gl?.glLoadIdentity();
        gl?.glFrustumf(-ratio, ratio, -1f, 1f, 1f, 10f);
    }

    override fun onDrawFrame(gl: GL10?) {
        gl?.glClear(GL10.GL_COLOR_BUFFER_BIT or  GL10.GL_DEPTH_BUFFER_BIT)
        count ++
        GLES30.glClearColor(mRed, mGreen, mBlue, 1.0f)
        GLES30.glClear( GLES30.GL_COLOR_BUFFER_BIT )
        reduceColor()
    }
    fun  setColor(r:Float?= null, g:Float? = null, b:Float? = null)
    {
        mRed = r?:mRed
        mGreen = g?:mGreen
        mBlue = b?:mBlue
    }
    private fun reduceColor(){
        if(count % 100 != 0L)
             return
        if(mRed > 0f){
            mRed -= 0.1f
            return
        } else {
            mRed = 1.0f
        }
    }

}