plugins {
    kotlin("multiplatform") version "1.5.10"
    application
}

group = "me.alpar"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
}

kotlin {
    jvm("backend") {
        compilations.all {
            kotlinOptions.jvmTarget = "15"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js("frontend", IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val backendMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:1.5.2")
                implementation("io.ktor:ktor-html-builder:1.5.2")
                implementation("com.github.lamba92:ktor-spa:1.2.1")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
                implementation("ch.qos.logback:logback-classic:1.2.7")
            }
        }
        val backendTest by getting
        val frontendMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.0-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:5.2.0-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.0.5-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux:7.2.3-pre.206-kotlin-1.5.10")
            }
        }
        val frontendTest by getting
    }
}

application {
    mainClass.set("me.alpar.application.ServerKt")
}

tasks.named<Copy>("backendProcessResources") {
//    val frontendBrowserDistribution = tasks.named("frontendBrowserDistribution")
//    from(frontendBrowserDistribution)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.create<Copy>("copyJsToBackendResources") {
    val frontendBrowserDistribution = tasks.named("frontendBrowserDistribution")
    val backendProcessResources = tasks.named<Copy>("backendProcessResources")
    from(frontendBrowserDistribution)
    into("${backendProcessResources.get().destinationDir.path}/js")
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named("copyJsToBackendResources"))
    dependsOn(tasks.named<Jar>("backendJar"))
//    classpath(tasks.named<Jar>("backendJar"))
}