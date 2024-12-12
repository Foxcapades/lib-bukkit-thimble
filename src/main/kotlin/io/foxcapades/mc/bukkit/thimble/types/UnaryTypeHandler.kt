package io.foxcapades.mc.bukkit.thimble.types

interface UnaryTypeHandler<T : Any> : SimpleTypeHandler<T, T> {
  override fun serialize(value: T): T = value
}
