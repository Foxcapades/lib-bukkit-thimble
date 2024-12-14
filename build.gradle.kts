plugins {
  kotlin("jvm") version "2.1.0"
  `maven-publish`
}

val mcVersion = "1.21.1"

group = "io.foxcapades.mc.bukkit"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven { url = uri("/home/ellie/.cache/maven/repo") }
  maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
}

dependencies {
  compileOnly("org.spigotmc:spigot-api:${mcVersion}-R0.1-SNAPSHOT")
  compileOnly("org.bukkit:craftbukkit:${mcVersion}-R0.1-SNAPSHOT")

  implementation(kotlin("reflect"))

  testImplementation("org.spigotmc:spigot-api:${mcVersion}-R0.1-SNAPSHOT")
  testImplementation("org.bukkit:craftbukkit:${mcVersion}-R0.1-SNAPSHOT")
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

publishing {
  publications {
    create<MavenPublication>("gpr") {
      from(components["java"])
      pom {
        name.set("Thimble")
        description.set("Provides minimal sized serialization of Bukkit and JVM types")
        url.set("https://github.com/Foxcapades/lib-bukkit-thimble")

        licenses {
          license {
            name.set("MIT")
          }
        }

        developers {
          developer {
            id.set("epharper")
            name.set("Elizabeth Paige Harper")
            email.set("foxcapades.io@gmail.com")
            url.set("https://github.com/foxcapades")
          }
        }

        scm {
          connection.set("scm:git:git://github.com/foxcapades/lib-bukkit-thimble.git")
          developerConnection.set("scm:git:ssh://github.com/foxcapades/lib-bukkit-thimble.git")
          url.set("https://github.com/Foxcapades/lib-bukkit-thimble")
        }
      }
    }
  }
}
