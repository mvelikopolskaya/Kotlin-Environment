import java.lang.StringBuilder

data class Person (var name: String, var phone: String?, var email: String?) {


    override fun toString(): String {
        var personData = StringBuilder()
        personData.appendLine("Name: $name")
        if(phone != null) personData.appendLine("Phone number: $phone")
        if(email != null) personData.appendLine("e-mail: $email")
        return personData.toString()
    }
}


