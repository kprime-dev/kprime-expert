package it.unibz.cs.semint.kprime.expert.domain

import com.fasterxml.jackson.annotation.JsonFormat
import unibz.cs.semint.kprime.domain.Gid
import unibz.cs.semint.kprime.domain.ddl.Label
import unibz.cs.semint.kprime.domain.ddl.Labelled
import unibz.cs.semint.kprime.domain.nextGid
import java.util.*

data class Todo(

        val id: Long = -1,
        val title: String = "",
        val completed: Boolean = false,
        val hidden: Boolean = false,
        val key: String = "",
        //val dateCreated: String = "")
        //val dateCreated:LocalDateTime = LocalDateTime.now())
        // "2020-03-21T20:03:08.638Z"
        @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        val dateCreated:Date = Date(),

        @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        var dateOpened:Date? = null,

        @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        var dateClosed:Date? = null,

        @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        var dateDue:Date? = null,

        var priority:Int = 0,
        var estimate:Int = 1,
        var partof:String = "",
        var assignee:String = "",
        var isOpened: Boolean = false,
        var isClosed: Boolean = false,
        var labels: String = "",
        val gid: Gid = nextGid()

): Labelled , Comparable<Todo>{


        override fun compareTo(other: Todo): Int {
                if (this.priority > other.priority) return -1
                if (this.priority < other.priority) return 1
                return 0
        }

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

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Todo

                if (id != other.id) return false
                if (title != other.title) return false
                if (completed != other.completed) return false
                if (hidden != other.hidden) return false
                if (key != other.key) return false
                if (dateCreated != other.dateCreated) return false
                if (dateOpened != other.dateOpened) return false
                if (dateClosed != other.dateClosed) return false
                if (dateDue != other.dateDue) return false
                if (priority != other.priority) return false
                if (estimate != other.estimate) return false
                if (partof != other.partof) return false
                if (assignee != other.assignee) return false
                if (isOpened != other.isOpened) return false
                if (isClosed != other.isClosed) return false
                if (labels != other.labels) return false
                if (gid != other.gid) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + title.hashCode()
                result = 31 * result + completed.hashCode()
                result = 31 * result + hidden.hashCode()
                result = 31 * result + key.hashCode()
                result = 31 * result + dateCreated.hashCode()
                result = 31 * result + (dateOpened?.hashCode() ?: 0)
                result = 31 * result + (dateClosed?.hashCode() ?: 0)
                result = 31 * result + (dateDue?.hashCode() ?: 0)
                result = 31 * result + priority
                result = 31 * result + estimate
                result = 31 * result + partof.hashCode()
                result = 31 * result + assignee.hashCode()
                result = 31 * result + isOpened.hashCode()
                result = 31 * result + isClosed.hashCode()
                result = 31 * result + labels.hashCode()
                result = 31 * result + gid.hashCode()
                return result
        }

        fun hasChildrenIn(listOfTodo: List<Todo> ):List<Todo> {
                return listOfTodo.filter { it.partof == this.title }
        }

}
