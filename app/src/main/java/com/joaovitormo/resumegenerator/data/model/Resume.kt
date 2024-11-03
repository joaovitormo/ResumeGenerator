package com.joaovitormo.resumegenerator.data.model

data class Resume(
    var name: String,
    var jobTitle: String,
    var email: String,
    var phone: String,
    var city: String,
    var summary: String,
    val experiences: List<Experience>,
    val education: List<Education>,
    val skills: List<Skill>,
    val languages: List<Language>
)

