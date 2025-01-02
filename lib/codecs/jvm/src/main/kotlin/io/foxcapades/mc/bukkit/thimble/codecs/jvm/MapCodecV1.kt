package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.DictionaryCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.RecordType
import java.io.OutputStream
import java.nio.ByteBuffer

class MapCodecV1<K, V, M: MutableMap<K, V>>(
  override val keyCodec: Codec<K>,
  override val valueCodec: Codec<V>,
  private val mapConstructor: (Int) -> M
) : DictionaryCodec<K, V, M> {
  override val dataType
    get() = DataType.Record(RecordType.Dictionary)

  override val version
    get() = U8CodecVersion.of(1u)

  @Suppress("UNCHECKED_CAST")
  override val javaType: Class<M>
    get() = Map::class.java as Class<M>

  override fun encodeBody(into: OutputStream, value: M) {
    ShortCodec.encodeBody(into, validateSize(value.size))
    for ((k, v) in value) {
      keyCodec.encode(into, k)
      valueCodec.encode(into, v)
    }
  }

  override fun create(from: ByteBuffer): M =
    ShortCodec.create(from).toInt().and(0xFFFF).let { mapConstructor(it).apply {
      for (i in 0 ..< it)
        put(keyCodec.create(from), valueCodec.create(from))
    } }

  @Suppress("NOTHING_TO_INLINE")
  private inline fun validateSize(size: Int): Short {
    if (size > 0xFFFF)
      throw IllegalArgumentException("list is too long to be written as a single value; length must be <= 65535")
    return size.toShort()
  }
}
