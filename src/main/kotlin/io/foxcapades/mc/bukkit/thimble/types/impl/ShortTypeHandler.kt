package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler

data object ShortTypeHandler : UnaryTypeHandler<Short> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "s"

  override val actualType get() = Short::class.java

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asShort() }
}
