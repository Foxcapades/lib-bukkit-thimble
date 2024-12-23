package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.types.DataType
import io.foxcapades.mc.bukkit.thimble.io.readU16I
import io.foxcapades.mc.bukkit.thimble.io.writeU16
import java.io.InputStream
import java.io.OutputStream

@JvmInline
value class ArrayCodecV1<T : Any>(override val valueCodec: Codec<T>) : SequenceCodec<T, Array<T>> {
  override val dataType
    get() = DataType.Sequence

  override val version
    get() = U8CodecVersion.of(1u)

  override fun encodeBody(into: OutputStream, value: Array<T>) {
    into.writeU16(requireValidSize(value.size))
    value.forEach { valueCodec.encodeBody(into, it) }
  }

  @Suppress("UNCHECKED_CAST")
  override fun create(from: InputStream): Array<T> =
    Array<Any?>(from.readU16I()) { valueCodec.create(from) } as Array<T>

  private fun requireValidSize(size: Int): Int {
    if (size > 0xFFFF)
      throw IllegalArgumentException("sequence is too long to be written as a single value; length must be <= 65535")

    return size
  }
}
