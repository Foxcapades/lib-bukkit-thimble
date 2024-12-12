package io.foxcapades.mc.bukkit.thimble.types

interface SpecialTypeHandler<D : Any> {
  fun serializeToRaw(value: D): String
}
