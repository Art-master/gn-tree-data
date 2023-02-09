import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.liquibase.gradle") version "2.0.4"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("kapt") version "1.5.21"
}

group = "com.history"
version = "0.0.1-SNAPSHOT"
description = "tree-data"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile>() {
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
    implementation("org.springframework.security:spring-security-oauth2-jose:5.6.2")

    //Metrics
    implementation("io.micrometer:micrometer-registry-prometheus:1.9.0")

    //Docs API
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.5.2")
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")

    //Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.6")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.21")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")
    //Kotlin + Reactor
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2")

    //Liquibase
    implementation("org.liquibase:liquibase-core:4.5.0")

    implementation("org.springframework:spring-orm:5.3.18") //???

    //Mapping
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")

    //Tracing
    implementation("io.opentracing.contrib:opentracing-spring-jaeger-cloud-starter:3.3.1")

    //Database
    runtimeOnly("org.postgresql:postgresql:42.3.3")
    runtimeOnly("io.r2dbc:r2dbc-postgresql:0.8.12.RELEASE")

    implementation("com.google.code.gson:gson:2.8.9")

    //TESTS
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.6")
    testImplementation("org.springframework.security:spring-security-test:5.6.2")
    testImplementation("io.projectreactor:reactor-test:3.4.16")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
