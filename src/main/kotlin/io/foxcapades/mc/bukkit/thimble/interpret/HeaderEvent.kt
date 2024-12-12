package io.foxcapades.mc.bukkit.thimble.interpret

internal data class HeaderEvent(val typeIndicator: String, val version: Int) : SyntaxEvent {
  override fun toString() = "HeaderEvent(type=$typeIndicator, version=$version)"
}
