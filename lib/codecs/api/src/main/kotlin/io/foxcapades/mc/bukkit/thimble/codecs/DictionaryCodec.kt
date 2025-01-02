package io.foxcapades.mc.bukkit.thimble.codecs

import java.io.OutputStream
import java.nio.ByteBuffer

interface DictionaryCodec<K, V, M: Map<K, V>>: U8VersionedCodec<M> {
  val keyCodec: Codec<K>

  val valueCodec: Codec<V>

  override fun encodeHeader(into: OutputStream) {
    super.encodeHeader(into)
    keyCodec.encodeHeader(into)
    valueCodec.encodeHeader(into)
  }

  override val headerLength: Int
    get() = super.headerLength +
      keyCodec.headerLength +
      valueCodec.headerLength

  override fun validateAndSkipHeader(from: ByteBuffer) {
    super.validateAndSkipHeader(from)
    keyCodec.validateAndSkipHeader(from)
    valueCodec.validateAndSkipHeader(from)
  }
}
