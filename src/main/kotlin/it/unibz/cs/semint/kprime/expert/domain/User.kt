package it.unibz.cs.semint.kprime.expert.domain

data class User(
        val id: String,
        val name:String,
        val role:String,
        val memberof:String,
        val pass:String,
        val email:String) {

    enum class ROLE { ANONYMOUS, EXECUTIVE, ARCHITECT, ENGINEER, TECHNICIAN }

    companion object {
        val CURRENT_USER = "currentUser"
        val NO_USER = "noUser"
            val LOGIN = "login"
    }
}