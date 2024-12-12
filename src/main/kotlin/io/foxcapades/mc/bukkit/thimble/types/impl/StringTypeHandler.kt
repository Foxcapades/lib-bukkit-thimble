package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler

data object StringTypeHandler : UnaryTypeHandler<String> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "S"

  override val actualType get() = String::class.java

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asString() }
}
