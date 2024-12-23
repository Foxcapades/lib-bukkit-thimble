package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.io.mustRead
import io.foxcapades.mc.bukkit.thimble.io.readI32
import io.foxcapades.mc.bukkit.thimble.io.writeI32
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream

object ByteArrayCodecV1 : SequenceCodec<Byte, ByteArray> {
  override val dataType
    get() = DataType.Sequence

  override val version
    get() = U8CodecVersion.of(1u)

  override val valueCodec: Codec<Byte>
    get() = ByteCodec

  override fun encodeBody(into: OutputStream, value: ByteArray) {
    into.writeI32(value.size)
    into.write(value)
  }

  override fun create(from: InputStream): ByteArray =
    ByteArray(from.readI32()).also { from.mustRead(it) }
}
