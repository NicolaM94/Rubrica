package views

import javafx.stage.Stage
import tornadofx.App
import tornadofx.launch
import views.logic.Contact
import views.logic.Settings
import java.io.File

class Engine :App(MainView::class) {

    val settings : Settings by inject()

    val userHome = settings.userHome

    init {
        val env = System.getProperty("os.name")
        when {
            env == "Windows" -> {
                if (!File("${userHome}\\SmartAddresses\\").exists()) {
                    File("${userHome}\\SmartAddresses\\").mkdir()
                    File("${userHome}\\SmartAddresses\\addresses.txt").createNewFile()
                    settings.projectFolder.set("${userHome}\\SmartAddresses\\")
                    settings.addressesFile.set("${userHome}\\SmartAddresses\\addresses.txt")
                    settings.settingsFile.set("${userHome}\\SmartAddresses\\settings.txt")
                } else {
                    with (File("${userHome}\\SmartAddresses\\settings.txt").readLines()) {
                        settings.projectFolder.set(this[1])
                        settings.addressesFile.set(this[2])
                        settings.settingsFile.set(this[3])
                    }
                    with(File(settings.addressesFile.value)) {
                        val reader = this.readLines()
                        reader.forEach {
                            val split = it.split(",")
                            settings.contactsList.add(Contact(split[0],split[1],split[2]))
                        }
                    }
                }
            }
            else -> {
                if (!File("${userHome}/SmartAddresses").exists()) {
                    File("${userHome}/SmartAddresses").mkdir()
                    File("${userHome}/SmartAddresses/addresses.txt").createNewFile()
                    settings.projectFolder.set("/${userHome}/SmartAddresses")
                    settings.addressesFile.set("/${userHome}/SmartAddresses/addresses.txt")
                    settings.settingsFile.set("/${userHome}/SmartAddresses/settings.txt")
                } else {
                    with (File("/${userHome}/SmartAddresses/settings.txt").readLines()) {
                        settings.projectFolder.set(this[1])
                        settings.addressesFile.set(this[2])
                        settings.settingsFile.set(this[3])
                    }
                    with(File(settings.addressesFile.value)) {
                        val reader = this.readLines()
                        reader.forEach {
                            val split = it.split(",")
                            settings.contactsList.add(Contact(split[0],split[1],split[2]))
                        }
                    }
                }
            }
        }

    }

    override fun start(stage: Stage) {
        with(stage) {
            this.width = 600.0
            this.height = 400.0
        }
        super.start(stage)
    }

    override fun stop() {
        println("Writing to ${settings.addressesFile.value}")
        with(File(settings.addressesFile.value)) {
            this.writeText("")
            settings.contactsList.forEach {
                this.appendText("${it.name},${it.phoneNumber},${it.mailAddress}\n")
            }
        }
        println("Writing to ${settings.settingsFile.value}")
        with (File(settings.settingsFile.value)) {
            this.writeText("""${settings.userHome}
                |${settings.projectFolder.value}
                |${settings.addressesFile.value}
                |${settings.settingsFile.value}
            """.trimMargin())
        }
        super.stop()
    }

}

fun main () {
    launch<Engine>()
}