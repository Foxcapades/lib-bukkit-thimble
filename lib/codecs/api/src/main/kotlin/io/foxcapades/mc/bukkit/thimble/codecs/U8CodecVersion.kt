package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import java.io.InputStream
import java.io.OutputStream

sealed interface U8CodecVersion : CodecVersion {
  override val length: UInt
    get() = 1u

  val value: UByte

  override fun writeTo(into: OutputStream) = into.write(value.toInt())

  companion object {
    @JvmStatic
    fun readFrom(from: InputStream): U8CodecVersion =
      U8CodecVersionImpl(when (val b = from.read()) {
        -1   -> throw ThimbleDeserializationException("unexpected EOF while reading codec version")
        else -> b.toUByte()
      })

    @JvmStatic
    fun of(value: UByte): U8CodecVersion = U8CodecVersionImpl(value)
  }
}

@JvmInline
private value class U8CodecVersionImpl(override val value: UByte) : U8CodecVersion
