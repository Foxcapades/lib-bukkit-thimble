package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.RecordType
import io.foxcapades.mc.bukkit.thimble.io.mustGetInt
import io.foxcapades.mc.bukkit.thimble.utils.writeInt
import java.io.EOFException
import java.io.OutputStream
import java.nio.ByteBuffer

object ByteArrayCodecV1 : SequenceCodec<Byte, ByteArray> {
  @Suppress("NOTHING_TO_INLINE")
  internal inline fun mustRead(from: ByteBuffer, buffer: ByteArray) {
    if (from.remaining() < buffer.size)
      throw EOFException("unexpected EOF")

    from.get(buffer)
  }

  override val dataType
    get() = DataType.Record(RecordType.Sequence)

  override val javaType: Class<out ByteArray>
    get() = ByteArray::class.java

  override val version
    get() = U8CodecVersion.of(1u)

  override val valueCodec: Codec<Byte>
    get() = ByteCodec

  override fun encodeBody(into: OutputStream, value: ByteArray) {
    into.writeInt(value.size)
    into.write(value)
  }

  override fun create(from: ByteBuffer): ByteArray =
    ByteArray(from.mustGetInt()).also { mustRead(from, it) }
}
