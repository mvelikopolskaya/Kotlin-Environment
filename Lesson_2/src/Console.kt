import Command.*

class Console : Command {

    override fun execute(phonebook : Phonebook): String {
        val flag = true
        do {
            println("Enter your command:")
            println("Add phone" +
                    "\nAdd email" +
                    "\nShow" +
                    "\nHelp" +
                    "\nExit"
            )
            print("> ")
            val choice: String = readln().lowercase()
            val command : Command? = readCommand(choice)
            command?.execute(phonebook)
        } while (flag)
        return ""
    }

    private fun readCommand(commandString: String) : Command? {
        var command : Command? = null
        when (commandString) {
            "add phone" -> command = AddPhoneContact
            "add email" -> command = AddEmailContact
            "help" -> command = Help
            "exit" -> command = Exit
            "show" -> command = Show
            else -> println("Wrong command")
        }
        return command
    }
}