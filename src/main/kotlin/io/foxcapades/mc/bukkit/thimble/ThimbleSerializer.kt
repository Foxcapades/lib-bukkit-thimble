package io.foxcapades.mc.bukkit.thimble

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.*
import java.io.ByteArrayOutputStream
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class ThimbleSerializer {
  private val instanceCodecs = HashMap<Class<*>, Codec<*>>(32)

  @OptIn(ExperimentalEncodingApi::class)
  fun serializeToString(value: Any): String =
    Base64.encode(serializeToBytes(value))

  fun serializeToBytes(value: Any): ByteArray {
    return if (value::class.javaPrimitiveType != null)
      serializePrimitive(value)
    else
      serializeInstance(value)
  }

  @OptIn(ExperimentalEncodingApi::class)
  fun deserialize(value: String): Any =
    deserialize(Base64.decode(value))

  fun deserialize(value: ByteArray): Any {

  }

  @OptIn(ExperimentalEncodingApi::class)
  fun <T : Any> deserializeAs(value: String, type: Class<T>): T =
    deserializeAs(Base64.decode(value), type)

  fun <T : Any> deserializeAs(value: ByteArray, type: Class<T>): T {

  }

  private fun serializeInstance(value: Any): ByteArray {
    val codecs =
  }

  private fun serializePrimitive(value: Any): ByteArray =
    when (value) {
      is Int     -> ByteArrayOutputStream(5).also { IntCodec.encode(it, value) }
      is Boolean -> ByteArrayOutputStream(2).also { BooleanCodec.encode(it, value) }
      is Double  -> ByteArrayOutputStream(9).also { DoubleCodec.encode(it, value) }
      is Float   -> ByteArrayOutputStream(5).also { FloatCodec.encode(it, value) }
      is Long    -> ByteArrayOutputStream(9).also { LongCodec.encode(it, value) }
      is Byte    -> ByteArrayOutputStream(2).also { ByteCodec.encode(it, value) }
      is Short   -> ByteArrayOutputStream(3).also { ShortCodec.encode(it, value) }
      is Char    -> ByteArrayOutputStream(3).also { ShortCodec.encode(it, value.code.toShort()) }
      else       -> throw IllegalStateException()
    }.toByteArray()
}
