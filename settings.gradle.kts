// settings.gradle.kts
pluginManagement {
    repositories {
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "YourProjectName" // Altere para o nome do seu projeto
include(":app") // Inclua seu módulo principal

rootProject.name = "ResumeGenerator"
include(":app")
 