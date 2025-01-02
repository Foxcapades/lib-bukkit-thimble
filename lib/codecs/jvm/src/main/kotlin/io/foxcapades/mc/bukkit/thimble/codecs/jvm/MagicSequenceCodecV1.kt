package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.RecordType
import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodec
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.utils.writeShort
import java.io.OutputStream
import java.nio.ByteBuffer
import java.util.stream.Stream

internal class MagicSequenceCodecV1(override val valueCodec: Codec<Any?>) : SequenceCodec<Any?, Any> {
  override val dataType: DataType
    get() = DataType.Record(RecordType.Sequence)

  override val version: U8CodecVersion
    get() = U8CodecVersion.of(1u)

  override val javaType: Class<out Any>
    get() = Any::class.java

  override fun create(from: ByteBuffer): List<Any?> =
    ArrayList<Any?>(from.getShort().toInt().and(0xFFFF))
      .apply {
        for (i in indices)
          add(valueCodec.decode(from))
      }

  override fun encodeBody(into: OutputStream, value: Any) {
    when (value) {
      is Collection<*> -> encodeSized(into, value.iterator(), value.size)
      is Array<*>      -> encodeSized(into, value.iterator(), value.size)
      is Stream<*>     -> encodeUnsized(into, value.iterator())
      is Iterable<*>   -> encodeUnsized(into, value.iterator())
      is Iterator<*>   -> encodeUnsized(into, value)
      is Sequence<*>   -> encodeUnsized(into, value.iterator())
      is IntArray      -> encodeSized(into, value.iterator(), value.size)
      is ByteArray     -> encodeSized(into, value.iterator(), value.size)
      is DoubleArray   -> encodeSized(into, value.iterator(), value.size)
      is LongArray     -> encodeSized(into, value.iterator(), value.size)
      is FloatArray    -> encodeSized(into, value.iterator(), value.size)
      is BooleanArray  -> encodeSized(into, value.iterator(), value.size)
      is ShortArray    -> encodeSized(into, value.iterator(), value.size)
      is CharArray     -> encodeSized(into, value.iterator(), value.size)
      else             -> throw IllegalArgumentException("cannot use value of type ${value::class} as a sequence")
    }
  }

  private fun encodeUnsized(into: OutputStream, value: Iterator<*>) {
    val tmp = ArrayList<Any?>(32)
    value.forEach { tmp.add(it) }
    encodeSized(into, value.iterator(), tmp.size)
  }

  private fun encodeSized(into: OutputStream, value: Iterator<*>, size: Int) {
    into.writeShort(requireValidSize(size))
    value.forEach { valueCodec.encode(into, it) }
  }

  private fun requireValidSize(size: Int): Short {
    if (size > 0xFFFF)
      throw IllegalArgumentException("sequence is too long to be written as a single value; length must be <= 65535")
    return size.toShort()
  }
}
