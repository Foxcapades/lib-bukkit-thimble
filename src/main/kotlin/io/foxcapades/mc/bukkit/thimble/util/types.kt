package io.foxcapades.mc.bukkit.thimble.util

internal inline fun <reified T: Any> Any.takeAs() =
  when (this) { is T -> this ; else -> null }
