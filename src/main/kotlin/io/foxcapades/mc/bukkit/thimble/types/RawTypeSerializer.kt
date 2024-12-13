package io.foxcapades.mc.bukkit.thimble.types

interface RawTypeSerializer<D : Any> {
  fun serializeToRaw(value: D): String
}
