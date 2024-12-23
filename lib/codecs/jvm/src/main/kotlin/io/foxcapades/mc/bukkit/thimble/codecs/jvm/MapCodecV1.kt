package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.DictionaryCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.types.DataType
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.readU16I
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.writeU16
import java.io.InputStream
import java.io.OutputStream

class MapCodecV1<K : Any, V : Any>(
  override val keyCodec: Codec<K>,
  override val valueCodec: Codec<V>,
) : DictionaryCodec<K, V, Map<K, V>> {
  override val dataType get() = DataType.Dictionary
  override val version get() = U8CodecVersion.of(1u)

  override fun encodeBody(into: OutputStream, value: Map<K, V>) {
    into.writeU16(validateSize(value.size))
    for ((k, v) in value) {
      keyCodec.encode(into, k)
      valueCodec.encode(into, v)
    }
  }

  override fun create(from: InputStream): Map<K, V> =
    from.readU16I().let { HashMap<K, V>(it).apply {
      for (i in 0 ..< it)
        put(keyCodec.create(from), valueCodec.create(from))
    } }

  @Suppress("NOTHING_TO_INLINE")
  private inline fun validateSize(size: Int): Int {
    if (size > 0xFFFF)
      throw IllegalArgumentException("list is too long to be written as a single value; length must be <= 65535")
    return size
  }
}
