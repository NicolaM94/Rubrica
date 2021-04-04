package views.logic

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.io.File

class Settings :Controller() {

    val userHome = System.getProperty("user.home")
    val projectFolder = SimpleStringProperty()
    val addressesFile = SimpleStringProperty()
    val settingsFile = SimpleStringProperty()
    val contactsList  = observableListOf<Contact>()

    val smtpAddress = SimpleStringProperty()
    val smtpPort = SimpleStringProperty()
    val mailAddress = SimpleStringProperty()
    val mailPwd = SimpleStringProperty()

    val tempDestinationAddress = SimpleStringProperty()




}