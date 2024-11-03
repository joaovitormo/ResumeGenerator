// ResumeViewModel.kt
package com.joaovitormo.resumegenerator.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaovitormo.resumegenerator.data.model.Resume
import com.joaovitormo.resumegenerator.data.PdfGenerator
import com.joaovitormo.resumegenerator.data.model.Education
import com.joaovitormo.resumegenerator.data.model.Experience
import com.joaovitormo.resumegenerator.data.model.Language
import com.joaovitormo.resumegenerator.data.model.Skill


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
        updatedList.add(Experience("", "", "", null, ""))
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    fun addEducation() {
        val updatedList = _resumeState.value?.education?.toMutableList() ?: mutableListOf()
        updatedList.add(Education("", "", "", ""))
        _resumeState.value = _resumeState.value?.copy(education = updatedList)
    }

    fun addSkill() {
        val updatedList = _resumeState.value?.skills?.toMutableList() ?: mutableListOf()
        updatedList.add(Skill("", ""))
        _resumeState.value = _resumeState.value?.copy(skills = updatedList)
    }

    fun addLanguage() {
        val updatedList = _resumeState.value?.languages?.toMutableList() ?: mutableListOf()
        updatedList.add(Language("", ""))
        _resumeState.value = _resumeState.value?.copy(languages = updatedList)
    }

    // Funções para atualizar itens nas listas

    fun onExperienceChange(index: Int, updatedExperience: Experience) {
        val updatedList = _resumeState.value?.experiences?.toMutableList() ?: mutableListOf()
        updatedList[index] = updatedExperience
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    fun onEducationChange(index: Int, updatedEducation: Education) {
        val updatedList = _resumeState.value?.education?.toMutableList() ?: mutableListOf()
        updatedList[index] = updatedEducation
        _resumeState.value = _resumeState.value?.copy(education = updatedList)
    }

    fun onSkillChange(index: Int, updatedSkill: Skill) {
        val updatedList = _resumeState.value?.skills?.toMutableList() ?: mutableListOf()
        updatedList[index] = updatedSkill
        _resumeState.value = _resumeState.value?.copy(skills = updatedList)
    }

    fun onLanguageChange(index: Int, updatedLanguage: Language) {
        val updatedList = _resumeState.value?.languages?.toMutableList() ?: mutableListOf()
        updatedList[index] = updatedLanguage
        _resumeState.value = _resumeState.value?.copy(languages = updatedList)
    }

    // Funções para atualizar campos dentro de uma experiência específica

    fun onExperienceJobTitleChange(index: Int, newJobTitle: String) {
        val updatedList = _resumeState.value?.experiences?.toMutableList() ?: mutableListOf()
        val experience = updatedList.getOrNull(index) ?: return
        updatedList[index] = experience.copy(jobTitle = newJobTitle)
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    fun onExperienceCompanyChange(index: Int, newCompany: String) {
        val updatedList = _resumeState.value?.experiences?.toMutableList() ?: mutableListOf()
        val experience = updatedList.getOrNull(index) ?: return
        updatedList[index] = experience.copy(company = newCompany)
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    fun onExperienceStartDateChange(index: Int, newStartDate: String) {
        val updatedList = _resumeState.value?.experiences?.toMutableList() ?: mutableListOf()
        val experience = updatedList.getOrNull(index) ?: return
        updatedList[index] = experience.copy(startDate = newStartDate)
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    fun onExperienceEndDateChange(index: Int, newEndDate: String?) {
        val updatedList = _resumeState.value?.experiences?.toMutableList() ?: mutableListOf()
        val experience = updatedList.getOrNull(index) ?: return
        updatedList[index] = experience.copy(endDate = newEndDate)
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    fun onExperienceDescriptionChange(index: Int, newDescription: String) {
        val updatedList = _resumeState.value?.experiences?.toMutableList() ?: mutableListOf()
        val experience = updatedList.getOrNull(index) ?: return
        updatedList[index] = experience.copy(description = newDescription)
        _resumeState.value = _resumeState.value?.copy(experiences = updatedList)
    }

    //Education
    fun onEducationInstitutionChange(index: Int, newText: String) {
        val updatedList = _resumeState.value?.education?.toMutableList() ?: mutableListOf()
        val education = updatedList.getOrNull(index) ?: return
        updatedList[index] = education.copy(institution = newText)
        _resumeState.value = _resumeState.value?.copy(education = updatedList)
    }

    fun onEducationDegreeChange(index: Int, newText: String) {
        val updatedList = _resumeState.value?.education?.toMutableList() ?: mutableListOf()
        val education = updatedList.getOrNull(index) ?: return
        updatedList[index] = education.copy(degree = newText)
        _resumeState.value = _resumeState.value?.copy(education = updatedList)
    }

    fun onEducationStartYearChange(index: Int, newText: String) {
        val updatedList = _resumeState.value?.education?.toMutableList() ?: mutableListOf()
        val education = updatedList.getOrNull(index) ?: return
        updatedList[index] = education.copy(startYear = newText)
        _resumeState.value = _resumeState.value?.copy(education = updatedList)
    }

    fun onEducationEndYearChange(index: Int, newText: String) {
        val updatedList = _resumeState.value?.education?.toMutableList() ?: mutableListOf()
        val education = updatedList.getOrNull(index) ?: return
        updatedList[index] = education.copy(endYear = newText)
        _resumeState.value = _resumeState.value?.copy(education = updatedList)
    }

    //Skill
    fun onSkillNameChange(index: Int, newText: String) {
        val updatedList = _resumeState.value?.skills?.toMutableList() ?: mutableListOf()
        val skills = updatedList.getOrNull(index) ?: return
        updatedList[index] = skills.copy(name = newText)
        _resumeState.value = _resumeState.value?.copy(skills = updatedList)
    }

    fun onSkillLevelChange(index: Int, newText: String) {
        val updatedList = _resumeState.value?.skills?.toMutableList() ?: mutableListOf()
        val skills = updatedList.getOrNull(index) ?: return
        updatedList[index] = skills.copy(level = newText)
        _resumeState.value = _resumeState.value?.copy(skills = updatedList)
    }

    //Language
    fun onLanguageNameChange(index: Int, newText: String) {
        val updatedList = _resumeState.value?.languages?.toMutableList() ?: mutableListOf()
        val languages = updatedList.getOrNull(index) ?: return
        updatedList[index] = languages.copy(name = newText)
        _resumeState.value = _resumeState.value?.copy(languages = updatedList)
    }

    fun onLanguageProficiencyChange(index: Int, newText: String) {
        val updatedList = _resumeState.value?.languages?.toMutableList() ?: mutableListOf()
        val languages = updatedList.getOrNull(index) ?: return
        updatedList[index] = languages.copy(proficiency = newText)
        _resumeState.value = _resumeState.value?.copy(languages = updatedList)
    }


    // Função para gerar o PDF
    fun generatePDF(context: Context) {
        _resumeState.value?.let { resume ->
            pdfGenerator.generatePDF(context, resume)
        }
    }
}