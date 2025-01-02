package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.NullCodec
import java.io.OutputStream

internal data class NullableMappingEncoder<T, I, V>(
  override val name: String,
  private val getter: (T) -> I?,
  private val translate: (I) -> V,
  private val codec: (OutputStream, V) -> Unit,
) : FieldEncoder<T> {
  constructor(getter: Getter<T, I?>, mapper: (I) -> V, codec: Codec<V>) : this(getter.name, getter, mapper, codec::encode)
  constructor(getter: Getter<T, I?>, mapper: (I) -> V, codec: (OutputStream, V) -> Unit) : this(getter.name, getter, mapper, codec)

  override fun encode(stream: OutputStream, instance: T) {
    when (val v = getter(instance)) {
      null -> NullCodec.encode(stream)
      else -> codec(stream, translate(v))
    }
  }
}
