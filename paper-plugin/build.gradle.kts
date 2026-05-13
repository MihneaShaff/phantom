import vn.id.tozydev.phantom.gradle.paper.features.plugin.excludePaperweightInternalRepositories
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml
import xyz.jpenilla.resourcefactory.paper.PaperPluginYaml

plugins {
    `paper-plugin`
    shadow
}

repositories {
    xenondevsReleases()
}

dependencies {
    implementation(platform(libs.kotlin.bom)) {
        version {
            prefer(getKotlinPluginVersion())
        }
    }
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.reflect)
    implementation(platform(libs.kotlinx.coroutines.bom))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.configurate.core)
    implementation(libs.configurate.yaml)

    implementation(projects.phantomPaperCore)
    implementation(projects.phantomDatabaseJdbc) {
        exclude(group = "org.slf4j", module = "slf4j-api")
    }
    implementation(projects.phantomDatabaseExposed) {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-reflect")
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core")
        exclude(group = "org.slf4j", module = "slf4j-api")
    }
}

paperPlugin {
    metadata {
        name = "PhantomPaper"
        main = "vn.id.tozydev.phatom.paper.PhantomCorePaperPlugin"
        apiVersion = "1.21.11"
        author = "tozydev"
        website = "https://tozydev.id.vn/"
        foliaSupported = true
        load = BukkitPluginYaml.PluginLoadOrder.STARTUP
        dependencies {
            server("eco", PaperPluginYaml.Load.BEFORE, false, joinClasspath = false)
        }
    }
    runServer {
        acceptEula = true
    }
}

tasks {
    jar {
        archiveClassifier = "plain"
    }

    shadowJar {
        archiveClassifier = ""
    }

    assemble {
        dependsOn(shadowJar)
    }

    build {
        dependsOn(shadowJar)
    }

    afterEvaluate {
        generateDynamicLibrariesLoaderClass {
            excludePaperweightInternalRepositories()
        }
    }
}
