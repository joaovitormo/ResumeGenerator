// ResumeViewModel.kt
package com.joaovitormo.resumegenerator.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaovitormo.resumegenerator.data.model.Resume
import com.joaovitormo.resumegenerator.data.PdfGenerator


class ResumeViewModel : ViewModel() {
    private val _resumeState = MutableLiveData(
        Resume(
            name = "",
            jobTitle = "",
            email = "",
            phone = "",
            city = "",
            summary = "",
            experiences = mutableListOf(),
            education = mutableListOf(),
            skills = mutableListOf(),
            languages = mutableListOf()
        )
    )
    val resumeState: LiveData<Resume> = _resumeState

    private val pdfGenerator = PdfGenerator()

    // Funções para atualizar campos individuais
    fun onNameChange(newName: String) {
        _resumeState.value = _resumeState.value?.copy(name = newName)
    }

    fun onJobTitleChange(newJobTitle: String) {
        _resumeState.value = _resumeState.value?.copy(jobTitle = newJobTitle)
    }

    fun onEmailChange(newEmail: String) {
        _resumeState.value = _resumeState.value?.copy(email = newEmail)
    }

    fun onPhoneChange(newPhone: String) {
        _resumeState.value = _resumeState.value?.copy(phone = newPhone)
    }

    fun onCityChange(newCity: String) {
        _resumeState.value = _resumeState.value?.copy(city = newCity)
    }

    fun onSummaryChange(newSummary: String) {
        _resumeState.value = _resumeState.value?.copy(summary = newSummary)
    }

    // Funções para adicionar itens às listas
    fun addExperience() {
        val updatedList = _resumeState.value?.experiences?.toMutableList() ?: mutableListOf()
        updatedList.add("") // Adiciona uma experiência vazia
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    fun addEducation() {
        val updatedList = _resumeState.value?.education?.toMutableList() ?: mutableListOf()
        updatedList.add("") // Adiciona uma formação vazia
        _resumeState.value = _resumeState.value?.copy(education = updatedList)
    }

    fun addSkill() {
        val updatedList = _resumeState.value?.skills?.toMutableList() ?: mutableListOf()
        updatedList.add("") // Adiciona uma habilidade vazia
        _resumeState.value = _resumeState.value?.copy(skills = updatedList)
    }

    fun addLanguage() {
        val updatedList = _resumeState.value?.languages?.toMutableList() ?: mutableListOf()
        updatedList.add("") // Adiciona um idioma vazio
        _resumeState.value = _resumeState.value?.copy(languages = updatedList)
    }

    // Funções para atualizar itens nas listas
    fun onExperienceChange(index: Int, newExperience: String) {
        val updatedList = _resumeState.value?.experiences?.toMutableList() ?: mutableListOf()
        updatedList[index] = newExperience
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    fun onEducationChange(index: Int, newEducation: String) {
        val updatedList = _resumeState.value?.education?.toMutableList() ?: mutableListOf()
        updatedList[index] = newEducation
        _resumeState.value = _resumeState.value?.copy(education = updatedList)
    }

    fun onSkillChange(index: Int, newSkill: String) {
        val updatedList = _resumeState.value?.skills?.toMutableList() ?: mutableListOf()
        updatedList[index] = newSkill
        _resumeState.value = _resumeState.value?.copy(skills = updatedList)
    }

    fun onLanguageChange(index: Int, newLanguage: String) {
        val updatedList = _resumeState.value?.languages?.toMutableList() ?: mutableListOf()
        updatedList[index] = newLanguage
        _resumeState.value = _resumeState.value?.copy(languages = updatedList)
    }

    // Função para gerar o PDF
    fun generatePDF(context: Context) {
        _resumeState.value?.let { resume ->
            pdfGenerator.generatePDF(context, resume)
        }
    }
}