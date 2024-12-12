package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler

data object BooleanTypeHandler : UnaryTypeHandler<Boolean> {
  override val currentVersion get() = 1

  override val actualType get() = Boolean::class.java

  override val typeIndicator get() = "B"

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asBoolean() }
}
