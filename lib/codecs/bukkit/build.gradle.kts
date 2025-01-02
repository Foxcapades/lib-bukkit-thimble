plugins {
  kotlin("jvm")
}

dependencies {
  api(project(":codec-api"))

  api(libs.spigot.api.v1x21x3)

  implementation(project(":common"))
  implementation(project(":jvm-codecs"))
  implementation(project(":unsafe-1.21.3"))
}
