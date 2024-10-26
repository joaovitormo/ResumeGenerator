package com.joaovitormo.resumegenerator.data.model

data class Resume(
    var name: String,
    var jobTitle: String,
    var email: String,
    var phone: String,
    var city: String,
    var summary: String,
    val experiences: List<String>,
    val education: List<String>,
    val skills: List<String>,
    val languages: List<String>
)

