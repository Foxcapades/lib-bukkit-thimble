package io.foxcapades.mc.bukkit.thimble.utils

inline fun <T : Any> unwrap(crossinline fn: () -> T?): () -> T = { fn()!! }
