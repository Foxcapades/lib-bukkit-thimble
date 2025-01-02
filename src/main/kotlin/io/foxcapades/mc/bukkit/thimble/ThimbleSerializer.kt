package io.foxcapades.mc.bukkit.thimble

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.ScalarCodecProvider
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.Base64

class ThimbleSerializer(val codecProvider: ScalarCodecProvider<*> = DefaultCodecRegistry()) {

  @JvmOverloads
  fun serializeToString(value: Any?, initialSize: Int = 1024): String =
    Base64.getEncoder().encodeToString(serializeToBytes(value, initialSize))

  @JvmOverloads
  fun serializeToBytes(value: Any?, initialSize: Int = 1024): ByteArray =
    with (when (value) {
      null -> codecProvider.getCodec(Void::class.java)!!
      else -> codecProvider.getCodec(value::class.java)
        ?: codecProvider.getCodec(Void::class.java)!!
    }) {
      ByteArrayOutputStream(minSize(initialSize))
        .also { encode(it, value) }
        .toByteArray()
    }

  fun deserialize(value: String): Any? =
    deserialize(Base64.getDecoder().decode(value))

  fun deserialize(value: ByteArray): Any? {
    if (value.isEmpty())
      throw IllegalArgumentException("cannot deserialize an empty value.")

    return ByteBuffer.wrap(value)
      .let { bb -> codecProvider
        .let { it.getCodec(DataType.fromTag(bb.get().toUByte()), bb, it) }
          ?.decode(bb)
      }
  }

  fun <T: Any> deserializeAs(value: String, type: Class<T>): ParseResult<T> =
    deserializeAs(Base64.getDecoder().decode(value), type)

  fun <T: Any> deserializeAs(value: ByteArray, type: Class<T>): ParseResult<T> {
    val buffer = ByteBuffer.wrap(value)

    @Suppress("UNCHECKED_CAST")
    return ((codecProvider.let { it.getCodec(type, buffer, it) }
      ?: return ParseResult(null, ParseResult.State.NotParsed)) as Codec<T?>)
      .decode(buffer)
      .let { ParseResult(it, when { it == null -> ParseResult.State.ParsedAsNull; else -> ParseResult.State.ParsedAsValue }) }
  }

  @Suppress("NOTHING_TO_INLINE")
  private inline fun minSize(a: Int) = when { a > 32 -> a; else -> 32 }

  data class ParseResult<T: Any>(val value: T?, val parseState: State) {
    enum class State {
      ParsedAsValue,
      ParsedAsNull,
      NotParsed,
    }

    val wasParsed get() = parseState != State.NotParsed
  }
}
