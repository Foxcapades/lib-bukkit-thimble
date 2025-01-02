package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.RecordType
import io.foxcapades.mc.bukkit.thimble.ThimbleSerializationException
import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.io.mustGetShort
import io.foxcapades.mc.bukkit.thimble.utils.writeShort
import java.io.OutputStream
import java.nio.ByteBuffer

class ArrayCodecV1<T>(override val valueCodec: Codec<T>) : SequenceCodec<T, Array<T>> {
  override val dataType: DataType
    get() = DataType.Record(RecordType.Sequence)

  @Suppress("UNCHECKED_CAST")
  override val javaType: Class<out Array<T>>
    get() = Array::class.java as Class<Array<T>>

  override val version: U8CodecVersion
    get() = U8CodecVersion.of(1u)

  override fun encodeBody(into: OutputStream, value: Array<T>) {
    into.writeShort(checkLength(value.size))
    if (valueCodec.dataType == DataType.Unknown) {
      value.forEach { valueCodec.encode(into, it) }
    } else {
      value.forEach { valueCodec.encodeBody(into, it) }
    }
  }

  @Suppress("UNCHECKED_CAST")
  override fun create(from: ByteBuffer): Array<T> =
    (if (valueCodec.dataType == DataType.Unknown)
      Array<Any?>(from.getSize()) { valueCodec.create(from).also { valueCodec.decodeBody(from, it) } }
    else
      Array<Any?>(from.getSize()) { valueCodec.decode(from) }) as Array<T>

  private fun checkLength(size: Int): Short =
    if (size > 0xFFFF)
      throw ThimbleSerializationException("cannot serialize a sequence with more than ${0xFFFF} values")
    else
      size.toShort()

  @Suppress("NOTHING_TO_INLINE")
  private inline fun ByteBuffer.getSize(): Int =
    mustGetShort().toInt().and(0xFFFF)
}
