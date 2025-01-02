package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.codecs.U8VersionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetShort
import java.io.OutputStream
import java.nio.ByteBuffer

object StringCodecV1 : U8VersionedCodec<CharSequence> {
  private const val MaxLength = 0xFFFF

  override val javaType: Class<out CharSequence>
    get() = CharSequence::class.java

  override val version: U8CodecVersion
    get() = U8CodecVersion.of(1u)

  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.String)

  override fun encodeBody(into: OutputStream, value: CharSequence) {
    validateLength(value.length)
    val encoded = value.toString().encodeToByteArray()

    ShortCodec.encodeBody(into, validateLength(encoded.size))
    into.write(encoded)
  }

  override fun create(from: ByteBuffer) =
    ByteArray(from.mustGetShort().toInt().and(0xFFFF))
      .also { ByteArrayCodecV1.mustRead(from, it) }
      .decodeToString()

  @Suppress("NOTHING_TO_INLINE")
  private inline fun validateLength(size: Int): Short {
    if (size > MaxLength)
      throw IllegalArgumentException("illegal string length $size, must be <= $MaxLength")

    return size.toShort()
  }
}
