package model

class Phonebook : Iterable<Person>{
    private var phonebook: MutableSet<Person> = mutableSetOf()

    fun addPerson(name: String, phoneNumbers: MutableSet<String>, emailList: MutableSet<String>) {
        val person = Person(name, phoneNumbers, emailList)
        phonebook.add(person)
    }

    fun show(name: String): Person? {
        val person = findByName(name)
        return person
    }

    fun findByName(name: String): Person? {
        phonebook.forEach { person: Person ->
            if (person.name.equals(name, true))
                return person
        }
        return null
    }

    fun findByPhoneNumber(phone: String): Person? {
        phonebook.forEach { person: Person ->
            if (person.phoneNumbers.contains(phone))
                return person
        }
        return null
    }

    fun findByEmail(email: String): Person? {
        phonebook.forEach { person: Person ->
            if (person.emailList.contains(email))
                return person
        }
        return null
    }

    fun isNotEmpty(): Boolean {
        return phonebook.isNotEmpty()
    }

    override operator fun iterator(): Iterator<Person> {
        return PersonIterator(phonebook)
    }
}





