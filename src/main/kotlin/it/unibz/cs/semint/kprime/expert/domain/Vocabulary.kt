package it.unibz.cs.semint.kprime.expert.domain

import it.unibz.cs.semint.kprime.expert.domain.Description
import it.unibz.cs.semint.kprime.expert.domain.Namespace
import it.unibz.cs.semint.kprime.expert.domain.Prefix
import it.unibz.cs.semint.kprime.expert.domain.Reference

data class Vocabulary(
        val prefix: Prefix,
        val namespace: Namespace,
        val description: Description = "",
        val reference: Reference = "")