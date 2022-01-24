package it.unibz.cs.semint.kprime.expert.domain

import unibz.cs.semint.kprime.domain.Gid
import unibz.cs.semint.kprime.domain.nextGid

open class Term(
        val name:String,
        val category:String,
        val relation:String,
        val type:String,
        val url:String,
        val description:String,
        val labels:String,
        val gid : Gid = nextGid(),
        val synonyms : String = ""
) {

    var constraint :String? =""
    var typeExpanded :String? =""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Term
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "$name ='$description', labels='$labels')"
    }


}
