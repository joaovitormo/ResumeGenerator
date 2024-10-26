package com.joaovitormo.resumegenerator.data.model

data class Experience(
    val jobTitle: String,
    val company: String,
    val startDate: String,
    val endDate: String?, // Pode ser nulo para o trabalho atual
    val description: String
)
