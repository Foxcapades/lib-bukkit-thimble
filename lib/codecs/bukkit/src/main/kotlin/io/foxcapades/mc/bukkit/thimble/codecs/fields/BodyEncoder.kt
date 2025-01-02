package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import java.io.OutputStream

internal data class BodyEncoder<T, V>(
  override val name: String,
  private val getter: (T) -> V,
  private val codec: Codec<V>,
) : FieldEncoder<T> {
  constructor(getter: Getter<T, V>, codec: Codec<V>) : this(getter.name, getter, codec)

  override fun encode(stream: OutputStream, instance: T) = codec.encodeBody(stream, getter(instance))
}

