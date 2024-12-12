package io.foxcapades.mc.bukkit.thimble.interpret

@JvmInline
internal value class StringEvent(val value: String) : ValueEvent {
  override fun toString() = when {
    value.length > 16 -> "StringEvent(${value.take(13)}...)"
    else              -> "StringEvent($value)"
  }
}
