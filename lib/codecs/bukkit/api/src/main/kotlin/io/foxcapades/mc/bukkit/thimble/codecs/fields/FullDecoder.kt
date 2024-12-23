package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream

internal data class FullDecoder<T, V>(
  override val name: String,
  private val setter: (T, V) -> Any?,
  private val codec: Codec<V>,
) : FieldDecoder<T> {
  override val dataType get() = codec.dataType

  constructor(setter: Setter<T, V>, type: DataType, decoder: (InputStream) -> V)
  : this(setter.name, setter, DummyDecoder(type, decoder))

  constructor(setter: Setter<T, V>, codec: Codec<V>)
  : this(setter.name, setter, codec)

  override fun decode(from: InputStream, into: T, offset: UInt) {
    setter(into, codec.decode(from, offset))
  }
}
