package io.foxcapades.mc.bukkit.thimble.read

interface ComplexValueAccessor : ValueAccessor {
  override val isComplex: Boolean
    get() = true

  fun <T> asType(type: Class<T>): T

  fun <T> asTypeOrNull(type: Class<T>): T?

  override fun asComplex(): ComplexValueAccessor = this
}

