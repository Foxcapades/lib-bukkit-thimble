package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.SimpleTypeHandler

data object CharTypeHandler : SimpleTypeHandler<String, Char> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "c"

  override val actualType get() = Char::class.java

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asString()[0] }

  override fun serialize(value: Char) = value.toString()
}
