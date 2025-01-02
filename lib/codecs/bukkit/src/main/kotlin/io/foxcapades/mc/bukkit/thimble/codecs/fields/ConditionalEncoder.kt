package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.NullCodec
import java.io.OutputStream

internal data class ConditionalEncoder<T, V>(
  private val filter: (T) -> Boolean,
  private val getter: Getter<in T, V>,
  private val encoder: (OutputStream, V) -> Unit,
) : FieldEncoder<T> {
  override val name get() = getter.name

  constructor(filter: (T) -> Boolean, getter: Getter<in T, V>, encoder: Codec<V>)
    : this(filter, getter, encoder::encode)

  override fun encode(stream: OutputStream, instance: T) {
    if (filter(instance)) {
      encoder(stream, getter(instance))
    } else {
      NullCodec.encode(stream)
    }
  }
}
