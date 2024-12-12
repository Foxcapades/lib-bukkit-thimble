package io.foxcapades.mc.bukkit.thimble.write

import com.google.gson.stream.JsonWriter
import io.foxcapades.mc.bukkit.thimble.ThimbleSerializationException
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.types.ListTypeHandler
import io.foxcapades.mc.bukkit.thimble.types.TypeHandler
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass


internal inline fun <T : TypeHandler<D>, D : Any> JsonWriter.withType(handler: T, fn: JsonWriter.(handler: T) -> Unit) {
  beginArray()
    .value(handler.typeIndicator)
    .value(handler.currentVersion)

  fn(handler)

  endArray()
}

internal fun <T : Any> JsonWriter.complexValue(value: T, writer: ValueWriter, handler: ComplexTypeHandler<T>) {
  withType(handler) { it.serialize(value, writer) }
}

internal fun <T : Any> JsonWriter.listValue(value: List<T>, writer: ValueWriter, handler: ListTypeHandler<T>) {
  withType(handler) { it.serialize(value, writer) }
}

internal fun JsonWriter.simpleValue(value: Any?, type: Class<*>) {
  if (value == null)
    nullValue()
  else
    requireWriter(value, type)(value)
}


private val writerFns = mapOf<KClass<*>, JsonWriter.(Any) -> Unit> (
  String::class     to { value(it as String) },
  Int::class        to { value((it as Int).toLong()) },
  Boolean::class    to { value(it as Boolean) },
  Double::class     to { value(it as Double) },
  Long::class       to { value(it as Long) },
  Byte::class       to { value((it as Byte).toLong()) },
  BigInteger::class to { value(it as BigInteger) },
  BigDecimal::class to { value(it as BigDecimal) },
  Float::class      to { value((it as Float).toDouble()) },
  Short::class      to { value((it as Short).toLong()) },
  Char::class       to { value(it.toString()) },
)

@Suppress("NOTHING_TO_INLINE")
internal inline fun Class<*>.isSimple() = kotlin.isSimple()

@Suppress("NOTHING_TO_INLINE")
internal inline fun KClass<*>.isSimple() = this in writerFns

@Suppress("NOTHING_TO_INLINE")
internal inline fun requireWriter(value: Any, type: Class<*>) =
  writerFns[value::class]
    ?: throw ThimbleSerializationException("invalid simple type returned from" +
      " serialize method for type $type")



internal fun List<*>.findListType(): Class<*> {
  var type: Class<*>? = null

  for (value in this) {
    if (value != null) {
      if (type == null) {
        type = value::class.java
      } else if (type != value::class.java) {
        return Any::class.java
      }
    }
  }

  return type ?: Any::class.java
}
