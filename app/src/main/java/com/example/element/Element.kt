package com.example.element

import javax.microedition.khronos.opengles.GL10

class Element {
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





    open fun draw(gl: GL10, viewWidth:Int, viewHeight:Int){

    }
}