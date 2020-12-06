package com.example.myapplication
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class BackgroundRender :  GLSurfaceView.Renderer {
    private var mRed :Float = 0f
    private var mGreen :Float = 0f
    private var mBlue:Float = 0f
    private var TAG = "BackgroundRender"

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0f,0f,0f,1f)

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)

    }

    override fun onDrawFrame(gl: GL10?) {
        Log.e(TAG,"R = $mRed  G = $mGreen B = $mBlue")
        GLES30.glClearColor(mRed, mGreen, mBlue, 1.0f)
        GLES30.glClear( GLES30.GL_COLOR_BUFFER_BIT )

        //reduceColor()
        //Log.e(TAG,"onDrawFrame")

    }
    fun  setColor(r:Float?= null, g:Float? = null, b:Float? = null)
    {
        mRed = r?:mRed
        mGreen = g?:mGreen
        mBlue = b?:mBlue
    }
    private fun reduceColor(){
        if(mRed > 0f){
            mRed -=1f
            return
        } else {
            mRed = 255f
            return
            if(mGreen > 0f){
                mGreen -= 10f
                return
            }else{
                if(mBlue > 0f){
                    mBlue -= 10f
                    return
                }else{
                    setColor(255f,255f,255f)
                }
            }
        }
    }

}