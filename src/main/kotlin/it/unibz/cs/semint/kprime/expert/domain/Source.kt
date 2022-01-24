package it.unibz.cs.semint.kprime.expert.domain

data class Source(
        val id: String,
        val type:String,
        val name:String,
        val driver:String,
        val location:String,
        val user:String,
        val pass:String)