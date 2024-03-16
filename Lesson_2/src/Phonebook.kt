class Phonebook {
    private var phonebook = ArrayList<Person>()

    fun addPerson(name : String, phone : String?, email : String?){
        val person = Person(name, phone, email)
        phonebook.add(person)
    }

    fun show() : Person {
        return phonebook.last()
    }

    fun findByName(name: String): Person?{
        for (person: Person in phonebook){
            if(person.name.equals(name))
                return person
        }
        return null
    }

    fun isNotEmpty() : Boolean {
        return phonebook.isNotEmpty()
    }
}




