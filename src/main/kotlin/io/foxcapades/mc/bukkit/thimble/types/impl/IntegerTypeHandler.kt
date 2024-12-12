package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler

data object IntegerTypeHandler : UnaryTypeHandler<Int> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "i"

  override val actualType get() = Int::class.java

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asInt() }
}
