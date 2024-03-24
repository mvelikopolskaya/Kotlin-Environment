package console

import console.Command.*
import model.Phonebook

class Console : Command {

    override fun execute(phonebook : Phonebook) {
        val flag = true
        do {
            println("Enter your command:")
            println("Add phone" +
                    "\nAdd email" +
                    "\nFind" +
                    "\nShow" +
                    "\nExport" +
                    "\nHelp" +
                    "\nExit"
            )
            print("> ")
            val choice: String = readln().lowercase().trim()
            val command : Command? = readCommand(choice)
            command?.execute(phonebook)
        } while (flag)
    }

    private fun readCommand(commandString: String) : Command? {
        var command : Command? = null
        when (commandString) {
            "add phone" -> command = AddPhoneContact
            "add email" -> command = AddEmailContact
            "find" -> command = Find
            "help" -> command = Help
            "exit" -> command = Exit
            "show" -> command = Show
            "export" -> command = Export
            else -> println("Wrong command")
        }
        return command
    }
}