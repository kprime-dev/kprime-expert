package it.unibz.krdb.kprime.domain

data class Setting(val workingDir: String,
                    val projectName: String,
                    val traceName: String,
                    val databaseName: String,
                    val changesetName: String,
                    val storyName: String)