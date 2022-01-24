package it.unibz.cs.semint.kprime.expert.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import unibz.cs.semint.kprime.domain.Gid
import unibz.cs.semint.kprime.domain.ddl.Label
import unibz.cs.semint.kprime.domain.ddl.Labelled
import unibz.cs.semint.kprime.domain.nextGid

data class Project(val name: String,
                   val location: String,
                   val description: String= "",
                   val picurl: String="",
                   val activeTrace: String="",
                   val activeTermBase:String="",
                   val gid:Gid= nextGid(),
                   var labels: String = "",
                   val partOf: String = ""
): Labelled {

    companion object {
        const val CURRENT_WORKING_DIR = ""
        @JsonIgnore
        val NO_PROJECT = Project("", CURRENT_WORKING_DIR)
    }

    @JsonIgnore
    fun isNoProject() = name.isNullOrEmpty()


    override fun resetLabels(labelsAsString: String): String {
        labels = labelsAsString
        return labels
    }

    override fun addLabels(labelsAsString: String): String {
        labels += labelsAsString
        return labels
    }

    override fun addLabels(newLabels: List<Label>): String {
        return addLabels(newLabels.joinToString(","))
    }

    override fun hasLabel(label: String): Boolean {
        return labels.contains(label)
    }

    override fun labelsAsString(): String {
        return labels
    }

    override fun remLabels(newLabels: List<Label>): String {
        val labels2 = labels ?: return ""
        return resetLabels(labels2.split(",")
                .filter { !newLabels.contains(it) }
                .joinToString(","))
    }
}