package io.foxcapades.mc.bukkit.thimble.utils

inline fun <T : Any> unwrap(crossinline fn: () -> T?): () -> T = { fn()!! }

inline fun <T> exert(assertion: Boolean, fn: () -> T): T =
  if (assertion) fn() else throw IllegalStateException()

inline fun <reified T: Any> Any.takeAs(): T? =
  if (this is T) this else null
