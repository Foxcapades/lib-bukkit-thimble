package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream

internal class EnumByteBodyDecoder<T, V : Enum<V>>(
  private val setter: Setter<T, V>,
  private val entries: Array<V>,
) : FieldDecoder<T> {
  override val name get() = setter.name
  override val dataType get() = DataType.Byte

  override fun decode(from: InputStream, into: T, offset: UInt) {
    setter(into, entries.lookup(ByteCodec.create(from)))
  }

  companion object {
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun <T: Enum<T>> decodeFully(from: InputStream, entries: Array<T>, offset: UInt = 0u): T =
      lookup(ByteCodec.decode(from, offset), entries)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun <T: Enum<T>> decodeBody(from: InputStream, entries: Array<T>): T =
      lookup(ByteCodec.create(from), entries)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun <T: Enum<T>> Array<T>.lookup(id: Byte): T =
      lookup(id, this)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun <T: Enum<T>> lookup(id: Byte, values: Array<T>): T =
      when (val i = id.toInt()) {
        in values.indices -> values[i]

        else -> throw ThimbleDeserializationException("invalid enum id value for enum ${values[0]::class}: $i")
      }
  }
}
