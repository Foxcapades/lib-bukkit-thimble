plugins {
  kotlin("jvm") version "2.1.0"
}

group = "io.foxcapades.mc.bukkit"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven { url = uri("/home/ellie/.cache/maven/repo") }
  maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
}

dependencies {
  compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
  compileOnly("org.bukkit:craftbukkit:1.21.1-R0.1-SNAPSHOT")

  implementation(kotlin("reflect"))

  testImplementation("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
  testImplementation("org.bukkit:craftbukkit:1.21.1-R0.1-SNAPSHOT")
  testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
}

kotlin {
  compilerOptions {
    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.test {
  useJUnitPlatform()
}
