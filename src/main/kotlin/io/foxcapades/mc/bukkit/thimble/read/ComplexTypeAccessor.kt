package io.foxcapades.mc.bukkit.thimble.read

internal class ComplexTypeAccessor(private val actualValue: Any) : ComplexValueAccessor {
  override fun <T> asType(type: Class<T>): T =
    type.cast(actualValue)

  override fun <T> asTypeOrNull(type: Class<T>): T? =
    try { asType(type) }
    catch (e: ClassCastException) { null }
}
