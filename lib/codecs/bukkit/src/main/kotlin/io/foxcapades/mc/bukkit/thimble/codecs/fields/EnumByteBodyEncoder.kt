package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.ThimbleSerializationException
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import java.io.OutputStream

internal class EnumByteBodyEncoder<T, V : Enum<V>>(
  private val getter: Getter<T, V>,
  // required to protect against issues with indices changing for bukkit enums
  // between versions.
  private val entries: Array<V>,
) : FieldEncoder<T> {
  override val name get() = getter.name

  override fun encode(stream: OutputStream, instance: T) {
    encodeBody(stream, entries, getter(instance))
  }

  companion object {
    fun <V: Enum<V>> encodeFully(into: OutputStream, entries: Array<V>, value: V) {
      ByteCodec.encode(into, requireIndexOf(entries, value))
    }

    fun <V: Enum<V>> encodeBody(into: OutputStream, entries: Array<V>, value: V) {
      ByteCodec.encodeBody(into, requireIndexOf(entries, value))
    }

    private fun <V: Enum<V>> requireIndexOf(entries: Array<V>, value: V): Byte =
      when (val i = entries.indexOf(value)) {
        -1 -> throw ThimbleSerializationException(
          "enum value ${value::class.simpleName}.$value is not registered with" +
          " this encoder"
        )

        else -> i.toByte()
      }
  }
}

