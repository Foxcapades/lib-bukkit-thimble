package io.foxcapades.mc.bukkit.thimble.interpret

@JvmInline
internal value class TypeEndEvent(private val value: Byte = 0) : SyntaxEvent {
  override fun toString() = "TypeEndEvent()"
}
