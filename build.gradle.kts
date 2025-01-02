plugins {
  kotlin("jvm") version "2.1.0"
  `maven-publish`
}

allprojects {
  apply(plugin="org.jetbrains.kotlin.jvm")

  group = "io.foxcapades.mc.bukkit"
  version = "1.0.0-SNAPSHOT"

  repositories {
    mavenCentral()
    maven { url = uri("/home/ellie/.cache/maven/repo") }
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
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
}

dependencies {
  compileOnly(libs.spigot.api.v1x21x3)

  implementation(project(":jvm-codecs"))
  implementation(project(":bukkit-codecs"))

  testImplementation(libs.spigot.api.v1x21x3)
  testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
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
