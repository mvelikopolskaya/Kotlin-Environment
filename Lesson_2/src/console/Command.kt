package console

import model.Person
import model.Phonebook
import model.jsonmanager.JsonManager
import java.io.File
import kotlin.system.exitProcess


sealed interface Command {

    fun isValid(text: String): Boolean {
        val flag = true
        return flag
    }

    fun execute(phonebook: Phonebook)

    data object Help : Command {
        override fun execute(phonebook: Phonebook) {
            println("To choose command: "
                    + "\nType \"Add phone\" to add contact name and phone number. Phone number must have look like \"+xxx\""
                    + "\nType \"Add email\" to add contact name and e-mail. Email must have look like \"name@mail.domain\""
                    + "\nType \"Exit\"for exit"
                    )
        }
    }

    data object AddEmailContact : Command {
        override fun isValid(text: String): Boolean {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$"
            return text.matches(emailRegex.toRegex())
        }

        override fun execute(phonebook: Phonebook) {
            var email: String
            var flag: Boolean
            println("Insert contact name")
            val name = readln()
            val person : Person? = phonebook.findByName(name)
            do {
                print("Insert contact e-mail: ")
                email = readln()
                flag = isValid(email)
                if (!flag) Help.execute(phonebook)
            } while (!flag)
            if (person != null) {
                person.emailList.add(email)
            } else phonebook.addPerson(name, mutableSetOf(), mutableSetOf(email))
            println("E-mail: $email")
        }
    }

    data object AddPhoneContact : Command {
        override fun isValid(text: String): Boolean {
            return text.startsWith("+") && text.substring(1).contains("[0-9]".toRegex())
        }

        override fun execute(phonebook: Phonebook) {
            var phoneNumber: String
            var flag: Boolean
            println("Insert contact name")
            val name = readln()
            val person : Person? = phonebook.findByName(name)
            do {
                print("Insert contact phone number: ")
                phoneNumber = readln()
                flag = isValid(phoneNumber)
                if (!flag) Help.execute(phonebook)
            } while (!flag)
            if (person != null) {
                person.phoneNumbers.add(phoneNumber)
            } else phonebook.addPerson(name, mutableSetOf(phoneNumber), mutableSetOf())
            println("Phone: $phoneNumber")
        }
    }

    data object Find : Command {
        override fun execute(phonebook: Phonebook) {
            println("Enter required person's phone number or e-mail")
            val personData = readln()
            if(phonebook.isNotEmpty()){
                if(personData.startsWith("+")) println(phonebook.findByPhoneNumber(personData))
                else if (personData.contains("@")) println(phonebook.findByEmail(personData))
                else println("Wrong data")
            }
            else println("Not initialized")
        }
    }

    data object Show : Command {
        override fun execute(phonebook: Phonebook) {
            println("Enter required person's name")
            val personName = readln()
            if(phonebook.isNotEmpty()) println(phonebook.show(personName).toString())
            else println("Not initialized")
        }
    }

    data object Export : Command {
        override fun execute(phonebook: Phonebook) {
            val jsonString = JsonManager().phonebookToJson(phonebook)
            File("src/phonebook.json").writeText(jsonString)
        }
    }

    data object Exit : Command {
        override fun execute(phonebook: Phonebook) {
            exitProcess(-1)
        }
    }
}
