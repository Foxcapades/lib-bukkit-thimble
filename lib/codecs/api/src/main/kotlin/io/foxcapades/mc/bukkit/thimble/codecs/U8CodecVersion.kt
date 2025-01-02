package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.io.mustGetByte
import java.io.OutputStream
import java.nio.ByteBuffer

sealed interface U8CodecVersion : CodecVersion {
  override val length: Int
    get() = 1

  val value: UByte

  override fun writeTo(into: OutputStream) = into.write(value.toInt())

  companion object {
    @JvmStatic
    fun readFrom(from: ByteBuffer): U8CodecVersion =
      U8CodecVersionImpl(from.mustGetByte().toUByte())

    @JvmStatic
    fun of(value: UByte): U8CodecVersion = U8CodecVersionImpl(value)

    @JvmStatic
    fun of(value: Int): U8CodecVersion = U8CodecVersionImpl(value.toUByte())
  }
}

@JvmInline
private value class U8CodecVersionImpl(override val value: UByte) : U8CodecVersion
