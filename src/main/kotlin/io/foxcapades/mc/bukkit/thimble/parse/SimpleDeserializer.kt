package io.foxcapades.mc.bukkit.thimble.parse

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor

fun interface SimpleDeserializer<D : Any> : ThimbleDeserializer<D> {
  @Throws(Exception::class)
  operator fun invoke(raw: ValueAccessor): D
}
