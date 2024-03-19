import java.lang.StringBuilder

data class Person (var name: String, var phoneNumbers: MutableSet<String> = mutableSetOf(), var emailList: MutableSet<String> = mutableSetOf()) {

    override fun toString(): String {
        var personData = StringBuilder()
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


