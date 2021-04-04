package views.views

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import views.logic.Settings

class MailSender :View() {

    val settings :Settings by inject()

    val sendingMail = settings.mailAddress.value
    val destinationMail = SimpleStringProperty(settings.tempDestinationAddress.value)
    val subject = SimpleStringProperty()
    val message = SimpleStringProperty()

    override val root = form {
        fieldset ("Invia messaggio") {
            field ("Oggetto") {textfield(subject)}
            field ("Messaggio") { textarea(message)}
        }
    }
}