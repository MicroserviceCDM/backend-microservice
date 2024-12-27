dependencies {
    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    
    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

description = "configserver"