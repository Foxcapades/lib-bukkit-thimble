package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import java.io.OutputStream

internal data class MappingBodyEncoder<T, I, V>(
  override val name: String,
  private val getter: (T) -> I,
  private val translate: (T, I) -> V,
  private val codec: Codec<V>,
) : FieldEncoder<T> {
  constructor(getter: Getter<T, I>, mapper: (I) -> V, codec: Codec<V>)
    : this(getter.name, getter, { _, v -> mapper(v) }, codec)

  override fun encode(stream: OutputStream, instance: T) =
    codec.encodeBody(stream, translate(instance, getter(instance)))
}
