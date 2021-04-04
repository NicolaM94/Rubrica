package views

import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*
import views.views.NewContactView
import views.views.SettingsView
import java.io.File
import java.net.URI

class MainView :View() {

    override val root = vbox {
        title = "Rubrica @Nicola Moro"
        style {
            baseColor = Color.valueOf("#eeff98")
            backgroundImage += URI("Untitled.png")
            setSpacing(10.0)
            alignment = Pos.CENTER
        }

        button("Nuovo contatto") {
            style {
                baseColor = Color.SLATEBLUE
                setPrefSize(350.0,45.0)
            }
            action { replaceWith<NewContactView>() }
        }
        button("Trova contatto") {
            style {
                baseColor = Color.SLATEBLUE
                setPrefSize(350.0,45.0)
            }
            action { replaceWith<FindContactView>() }
        }
        button ("Impostazioni") {
            style {
                baseColor = Color.SLATEBLUE
                setPrefSize(350.0,45.0)
            }
            action { replaceWith<SettingsView>() }
        }

    }
}