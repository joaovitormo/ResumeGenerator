package com.joaovitormo.resumegenerator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.joaovitormo.resumegenerator.ui.viewmodel.ResumeViewModel


@Composable
fun ResumeScreen(viewModel: ResumeViewModel) {
    val context = LocalContext.current
    val resumeState by viewModel.resumeState.observeAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            resumeState?.let { resume ->
                TextField(
                    value = resume.name,
                    onValueChange = { viewModel.onNameChange(it) },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = resume.jobTitle,
                    onValueChange = { viewModel.onJobTitleChange(it) },
                    label = { Text("Título do Trabalho") },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )

                TextField(
                    value = resume.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )

                TextField(
                    value = resume.phone,
                    onValueChange = { viewModel.onPhoneChange(it) },
                    label = { Text("Telefone") },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )

                TextField(
                    value = resume.city,
                    onValueChange = { viewModel.onCityChange(it) },
                    label = { Text("Cidade") },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )

                TextField(
                    value = resume.summary,
                    onValueChange = { viewModel.onSummaryChange(it) },
                    label = { Text("Resumo") },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )

                Text("Experiências", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
                resume.experiences.forEachIndexed { index, experience ->
                    TextField(
                        value = experience,
                        onValueChange = { viewModel.onExperienceChange(index, it) },
                        label = { Text("Experiência ${index + 1}") },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }
                Button(
                    onClick = { viewModel.addExperience() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Adicionar Experiência")
                }

                Text("Educação", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
                resume.education.forEachIndexed { index, education ->
                    TextField(
                        value = education,
                        onValueChange = { viewModel.onEducationChange(index, it) },
                        label = { Text("Educação ${index + 1}") },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }
                Button(
                    onClick = { viewModel.addEducation() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Adicionar Educação")
                }

                Text("Habilidades", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
                resume.skills.forEachIndexed { index, skill ->
                    TextField(
                        value = skill,
                        onValueChange = { viewModel.onSkillChange(index, it) },
                        label = { Text("Habilidade ${index + 1}") },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }
                Button(
                    onClick = { viewModel.addSkill() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Adicionar Habilidade")
                }

                Text("Idiomas", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
                resume.languages.forEachIndexed { index, language ->
                    TextField(
                        value = language,
                        onValueChange = { viewModel.onLanguageChange(index, it) },
                        label = { Text("Idioma ${index + 1}") },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }
                Button(
                    onClick = { viewModel.addLanguage() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Adicionar Idioma")
                }

                Button(
                    onClick = { viewModel.generatePDF(context) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Gerar PDF")
                }
            }
        }
    }
}





