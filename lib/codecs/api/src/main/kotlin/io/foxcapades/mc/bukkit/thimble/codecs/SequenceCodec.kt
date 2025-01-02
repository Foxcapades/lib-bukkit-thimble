package io.foxcapades.mc.bukkit.thimble.codecs

import java.io.OutputStream
import java.nio.ByteBuffer

interface SequenceCodec<T, C: Any> : U8VersionedCodec<C> {
  val valueCodec: Codec<T>

  override val headerLength
    get() = super.headerLength + valueCodec.headerLength

  override fun encodeHeader(into: OutputStream) {
    super.encodeHeader(into)
    valueCodec.encodeHeader(into)
  }

  override fun validateAndSkipHeader(from: ByteBuffer) {
    super.validateAndSkipHeader(from)
    valueCodec.validateAndSkipHeader(from)
  }
}

