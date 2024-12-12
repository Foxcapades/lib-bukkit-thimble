package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializer

sealed interface TypeHandler<D : Any> {
  val typeIndicator: String

  val currentVersion: Int

  val actualType: Class<D>

  fun deserializerFor(version: Int): ThimbleDeserializer<out D>?
}

