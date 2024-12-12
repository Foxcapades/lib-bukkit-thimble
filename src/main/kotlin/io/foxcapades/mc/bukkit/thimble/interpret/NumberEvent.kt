package io.foxcapades.mc.bukkit.thimble.interpret

@JvmInline
internal value class NumberEvent(val rawValue: String) : ValueEvent {
  override fun toString() = "NumberEvent($rawValue)"
}
