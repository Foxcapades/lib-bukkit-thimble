package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.NullCodec
import io.foxcapades.mc.bukkit.thimble.io.badNullableTypeError
import io.foxcapades.mc.bukkit.thimble.io.readDataType
import io.foxcapades.mc.bukkit.thimble.DataType
import java.io.InputStream

@Deprecated("get rid of it bruv")
internal data class NullableDecoder<T : Any, V : Any>(
  private val setter: Setter<T, V>,
  private val codec: Codec<V>,
) : FieldDecoder<T> {
  override val name get() = setter.name

  override val dataType = codec.dataType

  constructor(setter: Setter<T, V>, dataType: DataType, decoder: (InputStream) -> V)
    : this(setter, DummyDecoder(dataType, decoder))

  override fun decode(from: InputStream, into: T, offset: UInt) {
    when (val dt = from.readDataType()) {
      DataType.Null  -> NullCodec.decode(from, 1u + offset)
      codec.dataType -> setter(into, codec.decode(from, 1u + offset))
      else           -> throw badNullableTypeError(codec.dataType, dt)
    }
  }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun <T, V> NullableDecoder1(setter: Setter<T, V>, codec: Codec<V>): NullableDecoder1<T> =
  NullableDecoder1(FullDecoder(setter, codec))

@JvmInline
internal value class NullableDecoder1<T>(private val decoder: FieldDecoder<T>): FieldDecoder<T> {
  override val name get() = decoder.name
  override val dataType get() = decoder.dataType

  override fun decode(from: InputStream, into: T, offset: UInt) {
    when (val dt = from.readDataType()) {
      DataType.Null -> NullCodec.decode(from, 1u + offset)
      dataType      -> decoder.decode(from, into, 1u + offset)
      else          -> throw badNullableTypeError(dataType, dt)
    }
  }
}
