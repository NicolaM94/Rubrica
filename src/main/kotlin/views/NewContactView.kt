package views.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*
import views.MainView
import views.logic.Contact
import views.logic.Settings
import java.io.File
import java.net.URI

class NewContactView :View() {

    val settings :Settings by inject()

    val nameGuy = SimpleStringProperty("")
    val phoneNumber = SimpleStringProperty("")
    val mailAddress = SimpleStringProperty("")

    override val root = form {

        title = "Rubrica @Nicola Moro"

        style {
            alignment = Pos.TOP_CENTER
            backgroundImage += URI("/newcontact.png")
            setSpacing(10.0)
        }

        fieldset ("Inserisci i dati"){
            style { baseColor = Color.valueOf("#8ca5ff") }
            field {
                textfield(nameGuy).promptText = "es: Studio Martina Gottardo"
            }
            field {
                textfield(phoneNumber).promptText = "es: 0442-332679"
            }
            field {
                textfield(mailAddress).promptText="es: contabilita@studiomartinagottardo.it"
            }
        }

        button ("Salva") {
            style {
                setPrefWidth(150.0)
                setPrefHeight(30.0)
                baseColor = Color.valueOf("#8ca5ff")
            }
            action {
                settings.contactsList.add(Contact(nameGuy.value,phoneNumber.value,mailAddress.value))
                nameGuy.set("")
                phoneNumber.set("")
                mailAddress.set("")
                }
            }

        button ("Esci"){
            style {
                setPrefWidth(150.0)
                setPrefHeight(30.0)
                baseColor = Color.valueOf("#8ca5ff")
            }
            action { replaceWith<MainView>() }
        }
    }


}