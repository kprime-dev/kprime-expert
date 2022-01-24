package it.unibz.cs.semint.kprime.expert.domain

data class TransformerDesc(
        var name:String,
        val composeMatcher:String,
        val composeTemplate:String,
        val decomposeMatcher:String,
        val decomposeTemplate:String)