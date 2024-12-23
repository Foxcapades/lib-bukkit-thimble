package io.foxcapades.mc.bukkit.thimble.codecs

import java.io.InputStream
import java.io.OutputStream

interface DictionaryCodec<K : Any, V : Any, M : Map<K, V>> : U8VersionedCodec<M> {
  val keyCodec: Codec<K>

  val valueCodec: Codec<V>

  override fun encodeHeader(into: OutputStream) {
    super.encodeHeader(into)
    keyCodec.encodeHeader(into)
    valueCodec.encodeHeader(into)
  }

  override val headerLength: UInt
    get() = super.headerLength +
      keyCodec.headerLength +
      valueCodec.headerLength

  override fun validateAndSkipHeader(from: InputStream, offset: UInt) =
    super.validateAndSkipHeader(from, offset) +
      keyCodec.validateAndSkipHeader(from, 0u) +
      valueCodec.validateAndSkipHeader(from, 0u)
}
