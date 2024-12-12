package io.foxcapades.mc.bukkit.thimble.interpret

@JvmInline
internal value class BooleanEvent(val value: Boolean) : ValueEvent {
  override fun toString() = "BooleanEvent($value)"
}
