package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer

interface SimpleTypeHandler<S : Any, D : Any> : TypeHandler<D> {
  override fun deserializerFor(version: Byte): SimpleDeserializer<out D>?

  fun serialize(value: D): S?

  fun serializeNull(): S? = null
}
