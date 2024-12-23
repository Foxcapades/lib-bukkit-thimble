plugins {
  kotlin("jvm")
}

dependencies {
  api(project(":common"))
  api(project(":codec-api"))
  api(project(":jvm-codecs"))
  api(project(":unsafe-1.21.3"))
  api(libs.spigot.api.v1x21x3)
  implementation(project(":"))
}
