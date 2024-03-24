package model

data class Person (var name: String, var phoneNumbers: MutableSet<String> = mutableSetOf(), var emailList: MutableSet<String> = mutableSetOf()) {

    override fun toString(): String {
        val personData = StringBuilder()
        personData.appendLine("Name: $name")
        if(phoneNumbers.isNotEmpty()) {
            personData.append("Phone number: ")
            phoneNumbers.forEach { phone : String ->
                personData.appendLine(phone)
            }
        }
        if(emailList.isNotEmpty()) {
            personData.append("E-mail: ")
            emailList.forEach { email : String ->
                personData.appendLine(email)
            }
        }
        return personData.toString()
    }
}

class PersonIterator(private val phonebook: MutableSet<Person>) : Iterator<Person> {
    private var index = 0

    override fun hasNext(): Boolean {
        return phonebook.size > index
    }

    override fun next(): Person {
        return phonebook.elementAt(index++)
    }
}


