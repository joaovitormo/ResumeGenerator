package com.joaovitormo.resumegenerator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.joaovitormo.resumegenerator.data.model.Education
import com.joaovitormo.resumegenerator.data.model.Experience
import com.joaovitormo.resumegenerator.data.model.Language
import com.joaovitormo.resumegenerator.data.model.Resume
import com.joaovitormo.resumegenerator.data.model.Skill
import com.joaovitormo.resumegenerator.ui.viewmodel.ResumeViewModel


@Composable
fun ResumeScreen(viewModel: ResumeViewModel) {

    val resumeState by viewModel.resumeState.observeAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item { resumeState?.let { BasicInfoSection(viewModel, it) } }
        item { resumeState?.let { SummarySection(viewModel, it) } }
        item {
            resumeState?.let {
                ExperienceSection(
                    viewModel,
                    it.experiences,
                    onDelete = { experience -> viewModel.deleteExperience(experience) }
                )
            }
        }
        //item { resumeState?.let { EducationSection(viewModel, it.education) } }
        item {
            resumeState?.let {
                EducationSection(
                    viewModel,
                    it.education,
                    onDelete = { education -> viewModel.deleteEducation(education) }
                )
            }
        }
        //item { resumeState?.let { SkillsSection(viewModel, it.skills) } }
        item {
            resumeState?.let {
                SkillsSection(
                    viewModel,
                    it.skills,
                    onDelete = { skill -> viewModel.deleteSkill(skill) }
                )
            }
        }
        //item { resumeState?.let { LanguagesSection(viewModel, it.languages) } }
        item {
            resumeState?.let {
                LanguagesSection(
                    viewModel,
                    it.languages,
                    onDelete = { language -> viewModel.deleteLanguage(language) }
                )
            }
        }
        item { resumeState?.let { FooterSection(viewModel) } }

    }
}

@Composable
fun BasicInfoSection(viewModel: ResumeViewModel, resume: Resume) {
    Column {
        Text("Basic Information", style = MaterialTheme.typography.titleLarge)

        TextField(
            value = resume.name,
            onValueChange = { viewModel.onNameChange(it) },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = resume.jobTitle,
            onValueChange = { viewModel.onJobTitleChange(it) },
            label = { Text("Job Title") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = resume.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = resume.phone,
            onValueChange = { viewModel.onPhoneChange(it) },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = resume.city,
            onValueChange = { viewModel.onCityChange(it) },
            label = { Text("City") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SummarySection(viewModel: ResumeViewModel, resume: Resume) {
    Column {
        Text("Summary", style = MaterialTheme.typography.titleLarge)
        TextField(
            value = resume.summary,
            onValueChange = { viewModel.onSummaryChange(it) },
            label = { Text("Summary") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ExperienceSection(
    viewModel: ResumeViewModel,
    experiences: List<Experience>,
    onDelete: (Experience) -> Unit
) {
    Column {
        Text("Experiences", style = MaterialTheme.typography.titleLarge)

        experiences.forEachIndexed { index, experience ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = experience.jobTitle,
                        onValueChange = { newValue ->
                            viewModel.onExperienceJobTitleChange(index, newValue)
                        },
                        label = { Text("Job Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = experience.company,
                        onValueChange = { newValue ->
                            viewModel.onExperienceCompanyChange(index, newValue)
                        },
                        label = { Text("Company") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = experience.startDate,
                        onValueChange = { newValue ->
                            viewModel.onExperienceStartDateChange(index, newValue)
                        },
                        label = { Text("Start Date") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = experience.endDate ?: "",
                        onValueChange = { viewModel.onExperienceEndDateChange(index, it.ifBlank { null }) },
                        label = { Text("End Date") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = experience.description,
                        onValueChange = { newValue ->
                            viewModel.onExperienceDescriptionChange(index, newValue)
                        },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                IconButton(onClick = { onDelete(experience) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
        Button(onClick = { viewModel.addExperience() }) {
            Text("Add Experience")
        }
    }
}


@Composable
fun EducationSection(viewModel: ResumeViewModel, educationList: List<Education>, onDelete: (Education) -> Unit) {
    Column {
        Text("Education", style = MaterialTheme.typography.titleLarge)

        educationList.forEachIndexed { index, education ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = education.institution,
                        onValueChange = { viewModel.onEducationInstitutionChange(index, it) },
                        label = { Text("Institution") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = education.degree,
                        onValueChange = { viewModel.onEducationDegreeChange(index, it) },
                        label = { Text("Degree") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = education.startYear,
                        onValueChange = { viewModel.onEducationStartYearChange(index, it) },
                        label = { Text("Start Year") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = education.endYear ?: "",
                        onValueChange = {
                            it.ifBlank { null }
                                ?.let { it1 -> viewModel.onEducationEndYearChange(index, it1) }
                        },
                        label = { Text("End Year") },
                        modifier = Modifier.fillMaxWidth()

                    )
                }
                IconButton(onClick = { onDelete(education) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
        Button(onClick = { viewModel.addEducation() }) {
            Text("Add Education")
        }

    }

}

@Composable
fun SkillsSection(viewModel: ResumeViewModel, skills: List<Skill>, onDelete: (Skill) -> Unit) {
    Column {
        Text("Skills", style = MaterialTheme.typography.titleLarge)
        skills.forEachIndexed { index, skill ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = skill.name,
                        onValueChange = { viewModel.onSkillNameChange(index, it) },
                        label = { Text("Skill") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = skill.level,
                        onValueChange = { viewModel.onSkillLevelChange(index, it) },
                        label = { Text("Level") },
                        modifier = Modifier.fillMaxWidth()
                    )

                }
                IconButton(onClick = { onDelete(skill) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
        Button(onClick = { viewModel.addSkill() }) {
            Text("Add Skill")
        }

    }
}


    @Composable
    fun LanguagesSection(
        viewModel: ResumeViewModel,
        languages: List<Language>,
        onDelete: (Language) -> Unit
    ) {
        Column {
            Text("Languages", style = MaterialTheme.typography.titleLarge)
            languages.forEachIndexed { index, language ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        TextField(
                            value = language.name,
                            onValueChange = { viewModel.onLanguageNameChange(index, it) },
                            label = { Text("Language") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        TextField(
                            value = language.proficiency,
                            onValueChange = { viewModel.onLanguageProficiencyChange(index, it) },
                            label = { Text("Proficiency") },
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                    IconButton(onClick = { onDelete(language) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }

            Button(onClick = { viewModel.addLanguage() }) {
                Text("Add Language")
            }
        }
    }



@Composable
fun FooterSection(viewModel: ResumeViewModel){
    val context = LocalContext.current
    Button(
        onClick = { viewModel.generatePDF(context) },
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Text("Gerar PDF")
    }
}




