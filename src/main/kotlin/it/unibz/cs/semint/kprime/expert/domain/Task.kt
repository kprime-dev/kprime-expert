package it.unibz.cs.semint.kprime.expert.domain

data class Task(var title:String,
                var traceName:String,
                var storyFileName:String,
                var goalId:String,
                var assignee:String,
                var sources:List<String>) {
    override fun toString(): String {
        return "Task(title='$title', goalId='$goalId', assignee='$assignee', sources=$sources)"
    }
}