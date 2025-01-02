package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import java.io.OutputStream
import java.nio.ByteBuffer

interface VersionedCodec<T, V: CodecVersion> : Codec<T> {
  val version: V

  val versionCodec: (ByteBuffer) -> V

  override val headerLength: Int
    get() = super.headerLength + version.length

  override fun encodeHeader(into: OutputStream) {
    super.encodeHeader(into)
    version.writeTo(into)
  }

  override fun validateAndSkipHeader(from: ByteBuffer) {
    super.validateAndSkipHeader(from)

    if (from.position() == super.headerLength) {
      when (val tv = versionCodec(from)) {
        version -> { /* yup */ }
        else -> throw ThimbleDeserializationException("expected version $version, got $tv")
      }
    }
  }
}
