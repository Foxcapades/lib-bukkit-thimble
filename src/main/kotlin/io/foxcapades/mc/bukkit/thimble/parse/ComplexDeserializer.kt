package io.foxcapades.mc.bukkit.thimble.parse

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor

interface ComplexDeserializer<D : Any> : ThimbleDeserializer<D> {
  fun append(index: Int, value: ValueAccessor)

  fun build(): D
}
