package com.example.myapplication
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.util.Log
import java.util.Random
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class BackgroundRender :  GLSurfaceView.Renderer {
    private var mRed :Float = 0f
    private var mGreen :Float = 0f
    private var mBlue:Float = 0f
    private var TAG = "BackgroundRender"
    private var count:Int = 0
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0f,0f,0f,1f)
        GLES30.glClear( GLES30.GL_COLOR_BUFFER_BIT )

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)

    }

    override fun onDrawFrame(gl: GL10?) {
        count ++
//        if(count % 50 == 0){
//            count = 0
//            val rand = Random()
//            mRed = rand.nextInt(100) /100f
//            mGreen = rand.nextInt(100) /100f
//            mBlue = rand.nextInt(100) /100f
//        }
        //Log.e(TAG,"R = $mRed  G = $mGreen B = $mBlue")
        GLES30.glClearColor(mRed, mGreen, mBlue, 1.0f)
        GLES30.glClear( GLES30.GL_COLOR_BUFFER_BIT )

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
            mRed -= 0.1f
            return
        } else {
            if(mGreen > 0f){
                mGreen -= 0.1f
                return
            }else{
                if(mBlue > 0f){
                    mBlue -= 0.1f
                    return
                }else{
                    setColor(1f,1f,1f)
                }
            }
        }
    }

}