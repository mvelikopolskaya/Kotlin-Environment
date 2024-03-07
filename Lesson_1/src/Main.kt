fun main() {
    phoneBook()
}

fun phoneBook() {
    var flag: Boolean = true;
    do {
        println("Enter your command:")
        println("1. Add phone number" +
                "\n2. Add e-mail" +
                "\n3. Help" +
                "\n4. Exit")
        print("> ")
        val choice: String =  readln()
        when (choice) {
            "1" -> {
                addPhoneContact()
                phoneBook()
            }
            "2" -> {
                addEmailContact()
                phoneBook()
            }
            "3" -> {
                help()
                phoneBook()
            }
            "4" -> {
                flag = false;
            }
        }
    }while(flag)
}

private fun help() {
    println("To choose command use: " +
            "\n1 to add contact name and phone number " +
            "\n2 to add contact name and email " +
            "\n4 for exit")
}

private fun addEmailContact() {
    print("Insert contact name: ")
    val name: String = readln()
    print("Insert contact e-mail:")
    var email: String
    do {
        email = readln()
        if(!email.contains("@")) println("Wrong email format. Email must contain \"@\"")
    } while (!email.contains("@"))
        println("Name: $name\nE-mail: $email")
}

private fun addPhoneContact() {
    print("Insert contact name: ")
    val name: String = readln()
    print("Insert contact phone number: ")
    var phoneNumber: String
    do {
        phoneNumber = readln()
        if(!phoneNumber.startsWith("+")) println("Wrong phone format. Phone number must starts with \"+\"");
    } while (!phoneNumber.startsWith("+"))
    println("Name: $name\nPhone: $phoneNumber")
}
