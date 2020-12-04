package com.example.myapplication

import android.opengl.GLES30
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

import android.renderscript.ScriptGroup
import com.example.myapplication.databinding.ActivityMainBinding
import javax.microedition.khronos.egl.EGL10
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.egl.EGLDisplay
import javax.microedition.khronos.egl.EGLSurface
import javax.microedition.khronos.opengles.GL10

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mRenderer = BackgroundRender()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.glSurfaceView.apply{
            setEGLContextClientVersion(3)
            setEGLConfigChooser(true)
            setRenderer(mRenderer)
            //renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
            requestRender()
        }

    }

    override fun onResume() {
        super.onResume()
        binding.glSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.glSurfaceView.onPause()
    }
}