import org.intellij.lang.annotations.Pattern
import kotlin.system.exitProcess

sealed interface Command {

    fun isValid(text: String): Boolean {
        val flag = true
        return flag
    }

    fun execute(phonebook: Phonebook): String?


    data object Help : Command {
        override fun execute(phonebook: Phonebook): String {
            println("To choose command: "
                    + "\nType \"Add phone\" to add contact name and phone number. Phone number must have look like \"+xxx\""
                    + "\nType \"Add email\" to add contact name and e-mail. Email must have look like \"name@mail.domain\""
                    + "\nType \"Exit\"for exit"
                    )
            return ""
        }
    }

    data object AddEmailContact : Command {
        override fun isValid(text: String): Boolean {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\."
            return text.matches(emailRegex.toRegex())
        }

        override fun execute(phonebook: Phonebook): String {
            var email: String
            var flag: Boolean
            val name = AddName.execute(phonebook)
            val person : Person? = phonebook.findByName(name)
            do {
                print("Insert contact e-mail: ")
                email = readln()
                flag = isValid(email)
                if (person != null) {
                    person.email = email
                }
                phonebook.addPerson(name, person?.phone, email)
                if (!flag) Help.execute(phonebook)
            } while (!flag)
            println("E-mail: $email")
            return email
        }
    }

    data object AddPhoneContact : Command {
        override fun isValid(text: String): Boolean {
            return text.startsWith("+") && text.substring(1).contains("[0-9]".toRegex())
        }

        override fun execute(phonebook: Phonebook): String {
            var phoneNumber: String
            var flag: Boolean
            val name = AddName.execute(phonebook)
            val person : Person? = phonebook.findByName(name)
                do {
                    print("Insert contact phone number: ")
                    phoneNumber = readln()
                    flag = isValid(phoneNumber)
                    if (person != null) {
                        person.phone = phoneNumber
                    }
                    phonebook.addPerson(name, phoneNumber, person?.email)
                    if (!flag) Help.execute(phonebook)
                } while (!flag)
            println("Phone: $phoneNumber")
            return phoneNumber
        }
    }

    data object AddName : Command {
        override fun execute(phonebook: Phonebook): String {
            println("Insert contact name")
            return readln()
        }
    }

    data object Show : Command {
        override fun execute(phonebook: Phonebook): String {
            if(phonebook.isNotEmpty()) println(phonebook.show())
            else println("Not initialized")
            return ""
        }
    }

    data object Exit : Command {
        override fun execute(phonebook: Phonebook): String {
            exitProcess(-1)
        }
    }
}
