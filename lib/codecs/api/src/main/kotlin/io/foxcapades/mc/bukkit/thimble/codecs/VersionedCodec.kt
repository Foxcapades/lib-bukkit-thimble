package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import java.io.InputStream
import java.io.OutputStream

interface VersionedCodec<T : Any, V : CodecVersion> : Codec<T> {
  val version: V

  val versionCodec: (InputStream) -> V

  override val headerLength: UInt
    get() = super.headerLength + version.length

  override fun encodeHeader(into: OutputStream) {
    super.encodeHeader(into)
    version.writeTo(into)
  }

  override fun validateAndSkipHeader(from: InputStream, offset: UInt): UInt {
    val off = super.validateAndSkipHeader(from, offset)

    if (off == super.headerLength) {
      when (val tv = versionCodec(from)) {
        version -> { /* yup */ }
        else -> throw ThimbleDeserializationException("expected version $version, got $tv")
      }

      return super.headerLength + version.length
    }

    return off
  }
}

