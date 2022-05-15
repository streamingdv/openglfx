
import com.huskerdev.openglfx.GLCanvasAnimator
import com.huskerdev.openglfx.lwjgl.interop.LWJGLInterop
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.SplitPane
import javafx.scene.layout.FlowPane
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import rendering.ExampleRenderer

fun main(){
    System.setProperty("prism.order", "d3d,sw")
    System.setProperty("prism.vsync", "false")
    Application.launch(InteropExampleApp::class.java)
}

class InteropExampleApp: Application(){

    private lateinit var stage: Stage

    override fun start(stage: Stage?) {
        this.stage = stage!!

        stage.width = 300.0
        stage.height = 300.0

        /*
        stage.scene = Scene(object: SplitPane(){
            init {
                items.add(createGL())
                items.add(createGL())
            }
        })

         */




        stage.scene = Scene(createGL())

        stage.show()
    }

    private fun createGL(): Region {
        val canvas = LWJGLInterop()
        canvas.animator = GLCanvasAnimator(60.0, started = true)

        canvas.prefWidth = 80.0
        canvas.prefHeight = 80.0

        canvas.onReshape { ExampleRenderer.reshape(canvas, it) }
        canvas.onRender { ExampleRenderer.render(canvas, it) }

        return canvas
    }
}