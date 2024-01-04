import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.8.10"

    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

group = "com.genappline"
version = "0.0.1-SNAPSHOT"
description = "main module"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

repositories {
    mavenCentral()
}

dependencies {

    //Spring boot
    val springVersion = "3.0.2"
    implementation("org.springframework.boot:spring-boot-starter-webflux:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator:$springVersion")
    kapt("org.springframework.boot:spring-boot-configuration-processor:$springVersion")
    implementation("org.springframework.security:spring-security-oauth2-jose:6.0.0")

    //Metrics
    implementation("io.micrometer:micrometer-registry-prometheus:1.10.5")

    //Docs API
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.15")

    //Kotlin
    val kotlinVersion = "1.8.10"
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    //Kotlin + Reactor
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.1")

    //Mapping
    val mapstructVersion = "1.5.3.Final"
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")

    //Tracing
    implementation("io.opentracing.contrib:opentracing-spring-jaeger-cloud-starter:3.3.1")

    //Database
    runtimeOnly("org.postgresql:r2dbc-postgresql:1.0.1.RELEASE")
    runtimeOnly("org.postgresql:r2dbc-pool:1.0.1.RELEASE")
    runtimeOnly("org.postgresql:r2dbc-spi:1.0.1.RELEASE")

    implementation("com.google.code.gson:gson:2.10.1")

    //TESTS
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
    testImplementation("org.springframework.security:spring-security-test:6.0.2")
    testImplementation("io.projectreactor:reactor-test:3.5.4")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
