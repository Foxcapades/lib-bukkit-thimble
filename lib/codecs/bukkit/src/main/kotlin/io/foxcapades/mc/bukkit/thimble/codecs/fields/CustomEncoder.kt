package io.foxcapades.mc.bukkit.thimble.codecs.fields

import java.io.OutputStream

internal data class CustomEncoder<T, V>(
  override val name: String,
  private val getter: (T) -> V,
  private val encoder: (OutputStream, V) -> Unit
) : FieldEncoder<T> {
  constructor(getter: Getter<T, V>, encoder: (OutputStream, V) -> Unit) : this(getter.name, getter, encoder)

  override fun encode(stream: OutputStream, instance: T) = encoder(stream, getter(instance))
}
