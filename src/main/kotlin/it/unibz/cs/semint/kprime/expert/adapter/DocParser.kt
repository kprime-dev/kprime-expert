package it.unibz.cs.semint.kprime.expert.adapter

class DocParser {

    fun parseAnswers(text:String):Map<String,String> {
        val map = mutableMapOf<String,String>()
        val lines = text.split(System.lineSeparator())
        var key = "-"
        for (line in lines) {
            if (line.startsWith("?")) key = line.substringAfterLast(":").trim()
            if (line.startsWith(">")) {
                val value = line.drop(1).trim()
                map[key] = value
            }
        }
        return map
    }


}