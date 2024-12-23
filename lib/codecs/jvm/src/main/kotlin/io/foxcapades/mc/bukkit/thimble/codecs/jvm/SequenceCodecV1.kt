package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream

abstract class SequenceCodecV1<T : Any, C : Collection<T>> : SequenceCodec<T, C> {
  override val dataType
    get() = DataType.Sequence

  override val version
    get() = U8CodecVersion.of(1u)

  override fun encodeBody(into: OutputStream, value: C) {
    ShortCodec.encodeBody(into, requireValidSize(value.size))
    value.forEach { valueCodec.encodeBody(into, it) }
  }

  override fun create(from: InputStream): C =
    @Suppress("UNCHECKED_CAST")
    newCollection(ShortCodec.create(from).toInt()).also { (it as MutableCollection<T>).apply {
      for (i in indices)
        add(valueCodec.create(from).also { valueCodec.decodeBody(from, it) })
    } }

  protected abstract fun newCollection(size: Int): C

  private fun requireValidSize(size: Int): Short {
    if (size > 0xFFFF)
      throw IllegalArgumentException("sequence is too long to be written as a single value; length must be <= 65535")
    return size.toShort()
  }
}
