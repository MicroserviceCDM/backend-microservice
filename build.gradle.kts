import org.springframework.boot.gradle.tasks.bundling.BootJar

java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
}

plugins {
    base
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

tasks.bootJar {enabled = false}

allprojects {
    group = "io.github.cdm"

    repositories {
        mavenCentral()
    }

    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    extra["springCloudVersion"] = "2024.0.0"

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${project.ext["springCloudVersion"]}")
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
        
        // Test dependencies
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

// Add task to build all projects
tasks.register("buildAll") {
    group = "build"
    description = "Build all projects"
    dependsOn(subprojects.map { it.tasks.named("build") })
}

// Configure root project
tasks.named("build") {
    dependsOn(tasks.named("buildAll"))
}