package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler

data object LongTypeHandler : UnaryTypeHandler<Long> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "l"

  override val actualType get() = Long::class.java

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asLong() }
}
