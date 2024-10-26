package com.joaovitormo.resumegenerator.data

// PdfGenerator.kt
import android.content.Context
import android.os.Environment
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.ListItem
import com.itextpdf.layout.element.Paragraph
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

            // Adicionando dados ao PDF
            document.add(Paragraph("${resume.name}").setBold())
            document.add(Paragraph("Cargo: ${resume.jobTitle}"))
            document.add(Paragraph("Email: ${resume.email}"))
            document.add(Paragraph("Telefone: ${resume.phone}"))
            document.add(Paragraph("Cidade: ${resume.city}"))
            document.add(Paragraph("Resumo: ${resume.summary}").setItalic())

            // Adicionando seções de listas
            document.add(Paragraph("Experiências:"))
            val experienceList = PdfList()
            resume.experiences.forEach {
                experienceList.add(ListItem(it))
            }
            document.add(experienceList)

            document.add(Paragraph("Educação:"))
            val educationList = PdfList()
            resume.education.forEach {
                educationList.add(ListItem(it))
            }
            document.add(educationList)

            document.add(Paragraph("Habilidades:"))
            val skillsList = PdfList()
            resume.skills.forEach {
                skillsList.add(ListItem(it))
            }
            document.add(skillsList)

            document.add(Paragraph("Idiomas:"))
            val languagesList = PdfList()
            resume.languages.forEach {
                languagesList.add(ListItem(it))
            }
            document.add(languagesList)

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


