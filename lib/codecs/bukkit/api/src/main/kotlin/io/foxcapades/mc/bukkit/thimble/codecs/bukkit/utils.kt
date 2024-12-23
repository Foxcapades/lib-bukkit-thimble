package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.NullCodec
import io.foxcapades.mc.bukkit.thimble.io.badNullableTypeError
import io.foxcapades.mc.bukkit.thimble.io.readDataType
import io.foxcapades.mc.bukkit.thimble.types.DataType
import org.bukkit.Keyed
import org.bukkit.NamespacedKey
import org.bukkit.Registry
import java.io.InputStream
import java.io.OutputStream

internal object EmptyIterator : Iterator<Any?> {
  override fun next(): Any = throw NoSuchElementException()
  override fun hasNext(): Boolean = false
}

@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
internal inline fun <T> emptyIterator() = EmptyIterator as Iterator<T>

@Suppress("NOTHING_TO_INLINE")
internal inline fun <T> iteratorOf(vararg values: T): Iterator<T> =
  if (values.isEmpty()) emptyIterator() else values.iterator()

@Suppress("NOTHING_TO_INLINE")
internal inline fun <T : Keyed> Registry<T>.require(key: NamespacedKey): T =
  try { getOrThrow(key) } catch (e: Throwable) { throw ThimbleDeserializationException(e) }

internal fun <T> Codec<T>.nullable(from: InputStream): T? =
  when (val dt = from.readDataType()) {
    DataType.Null -> null
    dataType      -> decode(from, 1u)
    else          -> throw badNullableTypeError(dataType, dt)
  }

internal fun <T> Codec<T>.nullable(into: OutputStream, value: T?) {
  if (value == null) {
    NullCodec.encode(into)
  } else {
    encode(into, value)
  }
}

internal fun <T> Codec<T>.enforceNN(into: OutputStream, value: T?) {
  encode(into, value!!)
}
