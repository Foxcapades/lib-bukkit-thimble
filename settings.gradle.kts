rootProject.name = "thimble"

include(":common")
project(":common").projectDir = file("lib/common")

include(":codec-api")
project(":codec-api").projectDir = file("lib/codecs/api")

include(":jvm-codecs")
project(":jvm-codecs").projectDir = file("lib/codecs/jvm")

include(":bukkit-codecs")
project(":bukkit-codecs").projectDir = file("lib/codecs/bukkit")

include(":unsafe-1.21.3")
project(":unsafe-1.21.3").projectDir = file("lib/unsafe/1.21.3")

include(":unsafe-reference")
project(":unsafe-reference").projectDir = file("vendor/minecraft")
