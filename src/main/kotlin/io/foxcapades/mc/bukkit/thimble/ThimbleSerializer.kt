package io.foxcapades.mc.bukkit.thimble

import com.google.gson.stream.JsonWriter
import io.foxcapades.mc.bukkit.thimble.interpret.*
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.*
import io.foxcapades.mc.bukkit.thimble.types.*
import io.foxcapades.mc.bukkit.thimble.util.takeAs
import io.foxcapades.mc.bukkit.thimble.write.*
import java.io.StringWriter

class ThimbleSerializer @JvmOverloads constructor(private val registry: TypeHandlerRegistry = DefaultTypeHandlerRegistry) {
  // region Serialization

  fun serialize(value: Any): String {
    val out = StringWriter(4096)
    val json = JsonWriter(out)
    val writer = ValueWriterImpl(registry, json)

    if (value is List<*>) {
      @Suppress("UNCHECKED_CAST")
      json.listValue(value as List<Any>, writer, registry.requireListTypeHandlerFor(value.findListType()) as ListTypeHandler<Any>)
    } else {
      @Suppress("UNCHECKED_CAST")
      when (val handler = registry.requireTypeHandlerFor(value::class.java)) {
        is SimpleTypeHandler<*, *> -> json.serializeSimple(value, handler as SimpleTypeHandler<*, Any>)
        is ComplexTypeHandler<*>   -> json.complexValue(value, writer, handler as ComplexTypeHandler<Any>)
        else -> throw IllegalStateException()
      }
    }

    return out.toString()
  }

  private fun prepSerializers(size: Int): Pair<StringWriter, JsonWriter> =
    StringWriter(size).let { it to JsonWriter(it) }

  fun <T> serialize(value: T?, asType: Class<in T>): String {
    val handler = registry.requireTypeHandlerFor(asType)

    if (List::class.java.isAssignableFrom(asType)) {
      if (value == null) {
        val (out, json) = prepSerializers(32)
        registry.requireListTypeHandlerFor(Any::class.java).serializeNull(ValueWriterImpl(registry, json))
        return out.toString()
      } else {
        val (out, json) = prepSerializers(4096)
        @Suppress("UNCHECKED_CAST")
        json.listValue(value as List<Any>, ValueWriterImpl(registry, json), registry.requireListTypeHandlerFor(value.findListType()) as ListTypeHandler<Any>)
        return out.toString()
      }
    }

    @Suppress("UNCHECKED_CAST")
    return when (value) {
      null -> {
        val out = StringWriter(256)
        val json = JsonWriter(out)

        when (handler) {
          is SimpleTypeHandler<*, *> ->
            json.serializeSimpleNull(handler as SimpleTypeHandler<*, Any>)

          is ComplexTypeHandler<*> ->
            json.serializeComplexNull(handler as ComplexTypeHandler<Any>, ValueWriterImpl(registry, json))

          is ListTypeHandler<*> -> throw IllegalStateException()
        }

        out
      }

      else -> {
        val out = StringWriter(4096)
        val json = JsonWriter(out)

        when (handler) {
          is SimpleTypeHandler<*, *>
            -> json.serializeSimple(value, handler as SimpleTypeHandler<*, Any>)

          is ComplexTypeHandler<*>
            -> json.complexValue(value, ValueWriterImpl(registry, json), handler as ComplexTypeHandler<Any>)

          is ListTypeHandler<*> -> throw IllegalStateException()
        }

        out
      }
    }.toString()
  }

  private fun JsonWriter.serializeSimple(value: Any, handler: SimpleTypeHandler<*, Any>) {
    withType(handler) {
      if (it is SpecialTypeHandler<*>) {
        @Suppress("UNCHECKED_CAST")
        jsonValue((it as SpecialTypeHandler<Any>).serializeToRaw(value))
        return
      }

      when (val rendered = it.serialize(value)) {
        null -> nullValue()
        else -> requireWriter(rendered, handler.actualType)(rendered)
      }
    }
  }

  private fun JsonWriter.serializeComplexNull(handler: ComplexTypeHandler<Any>, writer: ValueWriter) {
    withType(handler) { it.serializeNull(writer) }
  }

  private fun JsonWriter.serializeSimpleNull(handler: SimpleTypeHandler<*, Any>) {
    withType(handler) {
      when (val rendered = it.serializeNull()) {
        null -> nullValue()
        else -> requireWriter(rendered, it.actualType)(rendered)
      }
    }
  }

  // endregion Serialization

  // region Deserialization

  fun deserialize(value: String): Any? {
    val source = Interpreter(value.reader())
    if (!source.hasNext())
      return null

    return when (source.next()) {
      is TypeStartEvent -> source.deserializeComplex()
      else -> throw IllegalStateException()
    }
  }

  private fun Interpreter.deserializeComplex(): Any {
    val header = next().takeAs<HeaderEvent>() ?: throw IllegalStateException()
    val handler = registry.requireTypeHandlerFor(header.typeIndicator)

    val deserializer = handler.deserializerFor(header.version)
      as ComplexDeserializer?
      ?: throw InvalidThimbleValueException("type handler for type" +
        " ${header.typeIndicator} does not support values of version" +
        " ${header.version}")

    var index = 0
    while (hasNext()) {
      when (val n = next()) {
        is BooleanEvent -> deserializer.append(index++, BooleanAccessor(n.value))
        is NullEvent -> deserializer.append(index++, NullAccessor())
        is NumberEvent -> deserializer.append(index++, NumberAccessor(n.rawValue))
        is StringEvent -> deserializer.append(index++, StringAccessor(n.value))
        is TypeStartEvent -> deserializer.append(index++, ComplexTypeAccessor(deserializeComplex()))
        is TypeEndEvent -> break

        is HeaderEvent -> throw IllegalStateException()
      }
    }

    return deserializer.build()
  }

  // endregion Deserialization
}
