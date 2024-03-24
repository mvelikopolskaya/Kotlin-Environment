package model.jsonmanager

class PersonBuilder {
    private var personName: String = ""
    private val personPhoneList = mutableSetOf<String>()
    private val personEmailList = mutableSetOf<String>()

    fun name(text: String) {
        this.personName = text
    }

    fun phoneList(set: MutableSet<String>) {
        personPhoneList.addAll(set)
    }

    fun emailList(set: MutableSet<String>) {
        personEmailList.addAll(set)
    }

    fun personToJson() : String {
        val stringBuilder = StringBuilder()
        stringBuilder.appendLine("{")
        stringBuilder.append(JsonManager().stringToJson("Name", personName)).appendLine(",")
        stringBuilder.append(JsonManager().setToJson("Phone number", personPhoneList)).appendLine(",")
        stringBuilder.appendLine(JsonManager().setToJson("E-mail", personEmailList))
        stringBuilder.append("}")
        return stringBuilder.toString()
    }
}