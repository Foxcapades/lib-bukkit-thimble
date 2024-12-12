package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler

data object ByteTypeHandler : UnaryTypeHandler<Byte> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "b"

  override val actualType get() = Byte::class.java

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asByte() }
}
