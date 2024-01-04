plugins {
    id("org.liquibase.gradle") version "2.0.4"
}

group = "com.genappline"
version = "0.0.1-SNAPSHOT"
description = "database module"

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

liquibase {
    activities.register("db_update") {
        val userName = System.getProperty("DB_USER")
        val userPass = System.getProperty("DB_PASSWORD")
        val dbHost = System.getProperty("DB_HOST")
        val dbPort = System.getProperty("DB_PORT")
        val dbName = System.getProperty("DB_NAME")

        this.arguments = mapOf(
            "logLevel" to "info",
            "driver" to "org.postgresql.Driver",
            "changeLogFile" to "${project.name}/src/main/resources/db/changelog/root.xml",
            "url" to "jdbc:postgresql://$dbHost:$dbPort/$dbName?createDatabaseIfNotExist=true",
            "username" to userName,
            "password" to userPass
        )
    }
}

repositories {
    mavenCentral()
}

dependencies {
    val postgresJdbcVersion = "42.5.4"
    liquibaseRuntime("org.liquibase:liquibase-core:4.19.0")
    liquibaseRuntime("info.picocli:picocli:4.6.1")
    liquibaseRuntime("org.postgresql:postgresql:${postgresJdbcVersion}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
