package com.joaovitormo.resumegenerator.data

// PdfGenerator.kt
import android.content.Context
import android.os.Environment
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.ListItem
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Text
import com.joaovitormo.resumegenerator.data.model.Resume
import java.io.File
import java.io.FileOutputStream
import com.itextpdf.layout.element.List as PdfList

class PdfGenerator {
    fun generatePDF(context: Context, resume: Resume) {
        try {
            // Caminho para salvar o PDF
            val pdfPath = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath
            val file = File(pdfPath, "${resume.name}_resume.pdf")
            val outputStream = FileOutputStream(file)

            // Criando o documento PDF
            val writer = PdfWriter(outputStream)
            val pdfDocument = com.itextpdf.kernel.pdf.PdfDocument(writer)
            val document = Document(pdfDocument)

            // Cria um único Paragraph
            val paragraph = Paragraph().setFixedLeading(12f) // Ajusta o espaçamento entre linhas

// Adiciona diferentes partes do conteúdo como Text, com formatação personalizada
            val nameText = Text("${resume.name}\n").setBold().setFontSize(14f)
            val jobTitleText = Text("Cargo: ${resume.jobTitle}\n")
            val emailText = Text("Email: ${resume.email}\n")
            val phoneText = Text("Telefone: ${resume.phone}\n")
            val cityText = Text("Cidade: ${resume.city}\n")
            val summaryText = Text("Resumo: ${resume.summary}").setItalic()

// Adiciona cada Text ao Paragraph
            paragraph.add(nameText)
            paragraph.add(jobTitleText)
            paragraph.add(emailText)
            paragraph.add(phoneText)
            paragraph.add(cityText)
            paragraph.add(summaryText)

// Adiciona o Paragraph ao documento
            document.add(paragraph)


            // Adiciona resumo
            if (resume.summary.isNotEmpty()) {
                document.add(Paragraph("Summary"))
                document.add(Paragraph(resume.summary))
            }

            // Adiciona Experiências
            if (resume.experiences.isNotEmpty()) {
                document.add(Paragraph("Experiences"))
                resume.experiences.forEach { experience ->
                    document.add(Paragraph("Job Title: ${experience.jobTitle}"))
                    document.add(Paragraph("Company: ${experience.company}"))
                    document.add(Paragraph("Start Date: ${experience.startDate}"))
                    document.add(Paragraph("End Date: ${experience.endDate ?: "Present"}"))
                    document.add(Paragraph("Description: ${experience.description}"))
                }
            }

            // Adiciona Educação
            if (resume.education.isNotEmpty()) {
                document.add(Paragraph("Education"))
                resume.education.forEach { education ->
                    document.add(Paragraph("Institution: ${education.institution}"))
                    document.add(Paragraph("Degree: ${education.degree}"))
                    document.add(Paragraph("Start Year: ${education.startYear}"))
                    document.add(Paragraph("End Year: ${education.endYear ?: "Present"}"))
                }
            }

            // Adiciona Habilidades
            if (resume.skills.isNotEmpty()) {
                document.add(Paragraph("Skills"))
                resume.skills.forEach { skill ->
                    document.add(Paragraph("${skill.name} - ${skill.level}"))
                }
            }

            // Adiciona Idiomas
            if (resume.languages.isNotEmpty()) {
                document.add(Paragraph("Languages"))
                resume.languages.forEach { language ->
                    document.add(Paragraph("${language.name} - ${language.proficiency}"))
                }
            }

            document.close()

            // Opção para visualizar o PDF gerado
            viewPDF(context, file.absolutePath)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun viewPDF(context: Context, filePath: String) {
        val file = File(filePath)
        val pdfUri = androidx.core.content.FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            file
        )

        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
        intent.setDataAndType(pdfUri, "application/pdf")
        intent.flags = android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
        intent.addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)

        context.startActivity(intent)
    }
}


