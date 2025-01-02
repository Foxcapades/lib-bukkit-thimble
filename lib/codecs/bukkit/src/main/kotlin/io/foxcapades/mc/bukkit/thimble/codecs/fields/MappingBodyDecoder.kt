package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import java.io.InputStream

internal data class MappingBodyDecoder<T, I, V>(
  override val name: String,
  private val setter: (T, V) -> Any?,
  private val translate: (I) -> V,
  private val codec: Codec<I>
) : FieldDecoder<T> {
  override val dataType get() = codec.dataType

  constructor(setter: Setter<T, V>, mapper: (I) -> V, codec: Codec<I>)
    : this(setter.name, setter, mapper, codec)

  override fun decode(from: InputStream, into: T, offset: UInt) {
    setter(into, translate(codec.create(from).also { codec.decodeBody(from, it) }))
  }
}
