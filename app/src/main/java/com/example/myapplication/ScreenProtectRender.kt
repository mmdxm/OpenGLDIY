package com.example.myapplication
import GLUtills.loadShader
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.example.element.Element
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.util.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kotlin.collections.ArrayList

class ScreenProtectRender :  GLSurfaceView.Renderer {
    companion object{
        const val TAG = "BackgroundRender"
        const val COORDS_PER_VERTEX = 3


    }

    private var count:Long  = 0

    private var width = 0
    private var height = 0

    private var mProgram :Int = 0
    private var triangleCoords = floatArrayOf(
            0f, 1f, 0.0f,  // top
            -1f, 0f, 0.0f,  // bottom left
            1f, 0f, 0.0f,// bottom right
            0f,-1f,0f
    )

    private var triangleColor = floatArrayOf(
            1.0f, 0.0f, 0.0f, 1.0f
    ) //白色

    private val vertexCount: Int = triangleCoords.size / COORDS_PER_VERTEX

    private val vertexStride = COORDS_PER_VERTEX * 4 // 每个顶点四个字节

    private lateinit var  vertexBuffer: FloatBuffer

    private val mElements: List<Element> = Collections.synchronizedList(ArrayList()) // 采用同步的list


    //顶点着色器
    private val verticesShaderSource = """ attribute vec4 vPosition;
 void main() {
     gl_Position = vPosition;
 }                                        
"""

    //片段着色器
    private val fragmentShaderSource = """ precision mediump float;
 uniform vec4 vColor;
 void main() {
     gl_FragColor = vColor;
 }                                           
"""




    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        count ++
        GLES20.glClearColor(1f, 1f, 0f, 1f)

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        reduceColor()        //将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram)

        //获取顶点着色器的vPosition成员句柄
        val mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer)
        //获取片元着色器的vColor成员的句柄
        val mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, triangleColor, 0)
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, vertexCount)
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle)

        for(ele in mElements){
            ele.draw(gl!!, width, height)
        }

    }



    private fun reduceColor(){
        //if(count % 10 != 0L)
         //    return

        triangleCoords[1] -= 0.004f
        if(triangleCoords[1] <= 0.0f){
            triangleCoords[1] = 1f
        }

        triangleCoords[3] += 0.004f
        if(triangleCoords[3] >= 0.0f){
            triangleCoords[3] = -1f
        }
        triangleCoords[6] -= 0.004f
        if(triangleCoords[6] <= 0.0f){
            triangleCoords[6] = 1f
        }


        triangleCoords[10] += 0.004f
        if(triangleCoords[10] >= 0.0f){
            triangleCoords[10] = -1f
        }

        vertexBuffer.put(triangleCoords)
        vertexBuffer.position(0)


        //triangleColor[1] = 0.1f + triangleColor[1]
        if(triangleColor[1] >= 1f){
            triangleColor[1] = 0f
        }
    }







    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0f, 0f, 0f, 1f)
        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        val bb = ByteBuffer.allocateDirect(triangleCoords.size * 4).order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(triangleCoords)
        vertexBuffer.position(0)
        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, verticesShaderSource)
        val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderSource)

        //创建一个空的OpenGLES程序
        mProgram = GLES20.glCreateProgram()
        //将顶点着色器加入到程序
        GLES20.glAttachShader(mProgram, vertexShader)
        //将片元着色器加入到程序中
        GLES20.glAttachShader(mProgram, fragmentShader)
        //连接到着色器程序
        GLES20.glLinkProgram(mProgram)

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        this.width = width
        this.height = height
        GLES20.glViewport(0, 0, width, height)
        // for a fixed camera, set the projection too
        val  ratio = width.toFloat() / height.toFloat()
        gl?.glMatrixMode(GL10.GL_PROJECTION)
        gl?.glLoadIdentity()
        gl?.glFrustumf(-ratio, ratio, -1f, 1f, 1f, 10f)
    }
}