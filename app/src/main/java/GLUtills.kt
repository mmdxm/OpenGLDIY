
import android.opengl.GLES20


object GLUtills {

    fun loadShader(shaderType: Int, source: String): Int {
        val shader = GLES20.glCreateShader(shaderType)
        if (shader == 0) {
            throw RuntimeException("Load shader error.")
        }
        val compiled = IntArray(1)
        GLES20.glShaderSource(shader, source)
        GLES20.glCompileShader(shader)
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0)
        if (compiled[0] == 0) {
            GLES20.glDeleteShader(shader)
            throw RuntimeException("Load shader error: " + GLES20.glGetShaderInfoLog(shader))
        }
        return shader
    }
}