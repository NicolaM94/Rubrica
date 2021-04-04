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
    val smtpServer = SimpleStringProperty()
    val smtpPort = SimpleStringProperty()
    val mailAddress = SimpleStringProperty()
    val pwdAddress = SimpleStringProperty()

    init {
        with(File(settings.settingsFile.value).readLines()) {
            smtpServer.set(this[4])
            smtpPort.set(this[5])
            mailAddress.set(this[6])
            pwdAddress.set(this[7])
        }
    }

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
            field ("Server SMTP") {textfield(smtpServer).promptText = "smtp.gmail.com"}
            field ("Porta SMTP") {textfield(smtpPort).promptText = "456"}
            field ("Indirizzo mail") { textfield(mailAddress).promptText = "mario.rossi@mail.com" }
            field ("Password mail") { passwordfield (pwdAddress).promptText = "SuperMari02354"}
        }

        button ("Esci") {
            style { setPrefWidth(150.0) }
            action {
                settings.smtpAddress.set(smtpServer.value)
                settings.smtpPort.set(smtpPort.value)
                settings.mailAddress.set(mailAddress.value)
                settings.mailPwd.set(pwdAddress.value)
                replaceWith<MainView>()
            }
        }
    }
}