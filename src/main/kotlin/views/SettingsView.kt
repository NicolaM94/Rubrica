package views.views
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*
import views.MainView
import views.logic.Settings
import java.io.File
import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path

class SettingsView :View() {

    val settings :Settings by inject()
    val addressesPath = SimpleStringProperty()

    @ExperimentalPathApi
    override val root = form {

        style {
            alignment = Pos.TOP_CENTER
        }

        fieldset ("Impostazioni locali") {
            field ("File indirizzi") { button("Scegli cartella").action {
                addressesPath.set(chooseDirectory()?.absolutePath)
                val sourcePath = Path(settings.addressesFile.value)
                var destinationPath = Path("")
                if (System.getProperty("os.name") == "Windows") {
                    destinationPath = Path(addressesPath.value+"\\addresses.txt")
                } else {
                    destinationPath = Path(addressesPath.value+"/addresses.txt")
                }
                println("Destination path: $destinationPath")
                Files.move(sourcePath,destinationPath,StandardCopyOption.REPLACE_EXISTING)
                settings.addressesFile.set(destinationPath.toString())
                println("Addresses file location changed to: ${settings.addressesFile.value}")
                }
            }
        }

        fieldset ("Impostazioni server") {
            field ("Server SMTP") {}
            field ("Porta SMTP") {}
            field ("Indirizzo mail") {  }
            field ("Password mail") { }
        }

        button ("Esci").action { replaceWith<MainView>() }
    }
}