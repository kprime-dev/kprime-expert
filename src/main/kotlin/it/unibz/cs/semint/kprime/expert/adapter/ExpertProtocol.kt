package it.unibz.cs.semint.kprime.expert.adapter


enum class NoteLevel {warning,error,critical,info}
enum class NoteLabel {ok,not_found}

data class ExpertOption(val optLabel:String, val optComand:String)
data class ExpertOptions(val options: List<ExpertOption>)
data class ExpertMessage(val uuid:String, val label:NoteLabel, val level:NoteLevel, val description:String, val contentType:String = "markdown")
data class ExpertPayload(val project: String, val trace: String, val termbase: String, val result: Any)
data class ExpertResponse(val msg:ExpertMessage, val payload:ExpertPayload?, val options:ExpertOptions = ExpertOptions(emptyList()))

