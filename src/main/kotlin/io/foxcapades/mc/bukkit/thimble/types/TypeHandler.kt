package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializer

sealed interface TypeHandler<D : Any> {
  val typeIndicator: String

  val currentVersion: Byte

  val actualType: Class<out D>

  fun deserializerFor(version: Byte): ThimbleDeserializer<out D>?
}

