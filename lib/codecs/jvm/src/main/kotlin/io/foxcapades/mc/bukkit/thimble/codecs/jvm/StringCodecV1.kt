package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.codecs.U8VersionedCodec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.mustRead
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.readU16I
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.writeU16
import java.io.InputStream
import java.io.OutputStream

object StringCodecV1 : U8VersionedCodec<String> {
  private const val MaxLength = 0xFFFF

  override val version
    get() = U8CodecVersion.of(1u)

  override val dataType
    get() = DataType.String

  override fun encodeBody(into: OutputStream, value: String) {
    validateLength(value.length)
    val encoded = value.encodeToByteArray()
    val size = validateLength(encoded.size)

    into.writeU16(size)
    into.write(encoded)
  }

  override fun create(from: InputStream) =
    ByteArray(from.readU16I())
      .also { from.mustRead(it) }
      .decodeToString()

  @Suppress("NOTHING_TO_INLINE")
  private inline fun validateLength(size: Int): Int {
    if (size > MaxLength)
      throw IllegalArgumentException("illegal string length $size, must be <= $MaxLength")

    return size
  }
}
