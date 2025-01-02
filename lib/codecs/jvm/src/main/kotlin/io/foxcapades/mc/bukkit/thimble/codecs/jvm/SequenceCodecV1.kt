package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.RecordType
import io.foxcapades.mc.bukkit.thimble.io.mustGetShort
import io.foxcapades.mc.bukkit.thimble.utils.writeShort
import java.io.OutputStream
import java.nio.ByteBuffer

abstract class SequenceCodecV1<T, C: Collection<T>> : SequenceCodec<T, C> {
  override val dataType: DataType
    get() = DataType.Record(RecordType.Sequence)

  override val version: U8CodecVersion
    get() = U8CodecVersion.of(1u)

  override fun encodeBody(into: OutputStream, value: C) {
    into.writeShort(requireValidSize(value.size))
    value.forEach { valueCodec.encodeBody(into, it) }
  }

  override fun create(from: ByteBuffer): C =
    @Suppress("UNCHECKED_CAST")
    newCollection(from.mustGetShort().toInt().and(0xFFFF)).also { (it as MutableCollection<T>).apply {
      if (valueCodec.dataType == DataType.Unknown) {
        for (i in indices)
          add(valueCodec.decode(from))
      } else {
        for (i in indices)
          add(valueCodec.create(from).also { valueCodec.decodeBody(from, it) })
      }
    } }

  protected abstract fun newCollection(size: Int): C

  private fun requireValidSize(size: Int): Short {
    if (size > 0xFFFF)
      throw IllegalArgumentException("sequence is too long to be written as a single value; length must be <= 65535")
    return size.toShort()
  }
}
