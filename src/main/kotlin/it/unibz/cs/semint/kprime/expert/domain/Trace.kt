package it.unibz.cs.semint.kprime.expert.domain

data class Trace(
        val name:String,
        val commands:List<String>,
        val log:List<String>)