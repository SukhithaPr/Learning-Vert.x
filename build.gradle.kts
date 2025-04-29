plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.vertx:vertx-core:4.5.3")
    implementation("io.vertx:vertx-web:4.5.3")
}

tasks.test {
    useJUnitPlatform()
}