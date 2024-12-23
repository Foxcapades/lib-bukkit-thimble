package io.foxcapades.mc.bukkit.thimble.codecs

import java.io.InputStream
import java.io.OutputStream

interface SequenceCodec<T : Any, C : Any> : U8VersionedCodec<C> {
  val valueCodec: Codec<T>

  override val headerLength: UInt
    get() = super.headerLength + valueCodec.headerLength

  override fun encodeHeader(into: OutputStream) {
    super.encodeHeader(into)
    valueCodec.encodeHeader(into)
  }

  override fun validateAndSkipHeader(from: InputStream, offset: UInt): UInt =
    super.validateAndSkipHeader(from, offset) +
      valueCodec.validateAndSkipHeader(from, 0u)
}

