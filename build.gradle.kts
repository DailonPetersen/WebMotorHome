
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposedVersion = "0.41.1"
val koinVersion = "3.3.1"

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
}

group = "com"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")

    //gson
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")

    implementation("io.ktor:ktor-server-swagger-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml:2.3.3")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    //Logging
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.4")
    implementation("io.github.microutils:kotlin-logging:2.0.12")

    // DATABASE DEPENDENCIES
    //exposed
    implementation ("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation ("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation ("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    //sqlite
    implementation ("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation ("org.xerial:sqlite-jdbc:3.36.0.3")

    //DI
    implementation ("io.insert-koin:koin-ktor:$koinVersion")
}
