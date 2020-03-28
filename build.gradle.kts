import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.71"
}

group = "io.juicebreak"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.+")
    implementation("com.github.kittinunf.fuel:fuel:2.2.1")
//    implementation("com.github.kittinunf.fuel:fuel-jackson:2.2.1")

    implementation("io.ktor:ktor-server-netty:1.3.2")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.ktor:ktor-jackson:1.3.2")
}

application {
    mainClassName = "io.juicebreak.MainKt"
}

defaultTasks("run")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
