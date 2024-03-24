package model.jsonmanager

import model.Phonebook


class JsonManager {
    fun setToJson(text: String, set: MutableSet<String>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("\"$text\": ")
        stringBuilder.append("[")
        for (text: String in set) {
            stringBuilder.append("\"").append(text).append("\"")
            if (set.last() != text) stringBuilder.append(",")
        }
        stringBuilder.append("]")
        return stringBuilder.toString()
    }

    fun stringToJson(text1: String, text2: String): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("\"$text1\": ").append("\"")
        stringBuilder.append(text2).append("\"")
        return stringBuilder.toString()
    }

    private fun personData(init: PersonBuilder.() -> Unit): String {
        val personBuilder = PersonBuilder().apply(init)
        return personBuilder.personToJson()
    }

    fun phonebookToJson(phonebook : Phonebook) : String{
        val stringBuilder = StringBuilder()
        stringBuilder.append("[")
        for (person in phonebook) {
            val jsonString = json {
                personData {
                    name(person.name)
                    phoneList(person.phoneNumbers)
                    emailList(person.emailList)
                }
            }
            stringBuilder.append(jsonString)
            if (phonebook.last() != person) stringBuilder.append(",")
        }
        stringBuilder.append("]")
        return stringBuilder.toString()
    }

    private fun json(init: JsonManager.() -> String): String {
        val jsonManager = JsonManager().let(init)
        return jsonManager
    }
}

