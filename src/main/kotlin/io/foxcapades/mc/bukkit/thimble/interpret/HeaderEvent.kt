package io.foxcapades.mc.bukkit.thimble.interpret

internal data class HeaderEvent(val typeIndicator: String, val version: Byte) : SyntaxEvent {
  override fun toString() = "HeaderEvent(type=$typeIndicator, version=$version)"
}
