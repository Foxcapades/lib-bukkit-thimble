package io.foxcapades.mc.bukkit.thimble.interpret

@JvmInline
internal value class NullEvent(private val none: Byte = 0) : ValueEvent {
  // for func references
  constructor(value: Any?) : this()

  fun <T : Any> typedNull() = null as T?

  override fun toString() = "NullEvent()"
}
