package io.foxcapades.mc.bukkit.thimble

import com.google.gson.stream.JsonWriter
import io.foxcapades.mc.bukkit.thimble.interpret.*
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.*
import io.foxcapades.mc.bukkit.thimble.types.*
import io.foxcapades.mc.bukkit.thimble.util.takeAs
import io.foxcapades.mc.bukkit.thimble.write.*
import java.io.StringWriter

class ThimbleSerializer
@JvmOverloads
constructor(private val registry: TypeDefinitionRegistry = DefaultTypeDefinitionRegistry())
{
  // region Serialization

  fun serialize(value: Any): String {
    val out = StringWriter(4096)
    val json = JsonWriter(out)
    val writer = ValueWriterImpl(registry, json)

    if (value is List<*>) {
      @Suppress("UNCHECKED_CAST")
      json.listValue(value as List<Any>, writer, registry.requireListTypeHandlerFor(value.findListType()) as ListTypeDefinition<Any>)
    } else {
      @Suppress("UNCHECKED_CAST")
      when (val handler = registry.requireTypeDefinitionFor(value::class.java)) {
        is SimpleTypeDefinition<*, *> -> json.serializeSimple(value, handler as SimpleTypeDefinition<*, Any>)
        is ComplexTypeDefinition<*>   -> json.complexValue(value, writer, handler as ComplexTypeDefinition<Any>)
        else -> throw IllegalStateException()
      }
    }

    return out.toString()
  }

  private fun prepSerializers(size: Int): Pair<StringWriter, JsonWriter> =
    StringWriter(size).let { it to JsonWriter(it) }

  fun <T> serialize(value: T?, asType: Class<in T>): String {
    val handler = registry.requireTypeDefinitionFor(asType)

    if (List::class.java.isAssignableFrom(asType)) {
      if (value == null) {
        val (out, json) = prepSerializers(32)
        registry.requireListTypeHandlerFor(Any::class.java).serializeNull(ValueWriterImpl(registry, json))
        return out.toString()
      } else {
        val (out, json) = prepSerializers(4096)
        @Suppress("UNCHECKED_CAST")
        json.listValue(value as List<Any>, ValueWriterImpl(registry, json), registry.requireListTypeHandlerFor(value.findListType()) as ListTypeDefinition<Any>)
        return out.toString()
      }
    }

    @Suppress("UNCHECKED_CAST")
    return when (value) {
      null -> {
        val out = StringWriter(256)
        val json = JsonWriter(out)

        when (handler) {
          is SimpleTypeDefinition<*, *> ->
            json.serializeSimpleNull(handler as SimpleTypeDefinition<*, Any>)

          is ComplexTypeDefinition<*> ->
            json.serializeComplexNull(handler as ComplexTypeDefinition<Any>, ValueWriterImpl(registry, json))

          is ListTypeDefinition<*> -> throw IllegalStateException()
        }

        out
      }

      else -> {
        val out = StringWriter(4096)
        val json = JsonWriter(out)

        when (handler) {
          is SimpleTypeDefinition<*, *>
            -> json.serializeSimple(value, handler as SimpleTypeDefinition<*, Any>)

          is ComplexTypeDefinition<*>
            -> json.complexValue(value, ValueWriterImpl(registry, json), handler as ComplexTypeDefinition<Any>)

          is ListTypeDefinition<*> -> throw IllegalStateException()
        }

        out
      }
    }.toString()
  }

  private fun JsonWriter.serializeSimple(value: Any, handler: SimpleTypeDefinition<*, Any>) {
    withType(handler) {
      if (it is RawTypeSerializer<*>) {
        @Suppress("UNCHECKED_CAST")
        jsonValue((it as RawTypeSerializer<Any>).serializeToRaw(value))
        return
      }

      when (val rendered = it.serialize(value)) {
        null -> nullValue()
        else -> requireWriter(rendered, handler.actualType)(rendered)
      }
    }
  }

  private fun JsonWriter.serializeComplexNull(handler: ComplexTypeDefinition<Any>, writer: ValueWriter) {
    withType(handler) { it.serializeNull(writer) }
  }

  private fun JsonWriter.serializeSimpleNull(handler: SimpleTypeDefinition<*, Any>) {
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
    val handler = registry.requireTypeDefinitionFor(header.typeIndicator)

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
