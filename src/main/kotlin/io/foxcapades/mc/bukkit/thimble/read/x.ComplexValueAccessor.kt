package io.foxcapades.mc.bukkit.thimble.read

inline fun <reified T : Any> ComplexValueAccessor.asType(): T =
  asType(T::class.java)

inline fun <reified T : Any> ComplexValueAccessor.asTypeOrNull(): T? =
  asTypeOrNull(T::class.java)
