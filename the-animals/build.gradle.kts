plugins {
	id("application")
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
}

tasks.test {
    useJUnitPlatform()
}

application {
	mainClass.set("agh.ics.oop.World")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}