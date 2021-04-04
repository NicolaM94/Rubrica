package views

import com.sun.tools.javac.Main
import javafx.geometry.Pos
import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent
import javafx.scene.input.DataFormat
import javafx.scene.paint.Color
import tornadofx.*
import views.logic.Contact
import views.logic.Settings
import javax.xml.crypto.Data

class FindContactView :View() {

    val settings :Settings by inject()

    override val root = vbox {
        title = "Rubrica @Nicola Moro"
        style {
            baseColor = Color.LIGHTYELLOW
            alignment = Pos.CENTER
            paddingAll = 10
            setSpacing(10.0)
        }
        tableview (settings.contactsList) {
            style {
                baseColor = Color.WHITESMOKE
            }
            column("Nome",Contact::name)
            column("Numero",Contact::phoneNumber)
            column("Indirizzo",Contact::mailAddress)
            enableCellEditing()


            contextmenu {
                item("Elimina").action {
                    settings.contactsList.removeIf {
                        it.name == selectedItem?.name
                    }
                }
                item ("Copia").action {
                    Clipboard.getSystemClipboard().setContent(mapOf(DataFormat.PLAIN_TEXT to selectedValue.toString()))
                }
                item ("Invia Mail").action {  }
            }
        }
        button ("Esci"){
            style {
                baseColor = Color.SALMON
                setPrefWidth(125.0)
            }
            action { replaceWith<MainView>() }
        }
    }


}